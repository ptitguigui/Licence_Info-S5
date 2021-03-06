import Parser
import Data.Char
import Data.Maybe
import System.IO

type Nom = String

data Expression = Lam Nom Expression
                | App Expression Expression
                | Var Nom
                | Lit Litteral
                deriving (Show,Eq)

data Litteral = Entier Integer
              | Bool   Bool
              deriving (Show,Eq)

-- zero ou plusieurs espaces
espacesP :: Parser ()
espacesP = do many (car ' ')
              pure (())

-- un Nom, pas d'espaces au debut et consomme espaces apres
nomP :: Parser Nom
nomP = do res <- some (carQuand isAlpha)
          espacesP
          pure (res)

-- un expression, pas d'espaces au debut et consomme espaces apres
varP :: Parser Expression
varP = do res <- nomP
          pure (Var res)

applique :: [Expression] -> Expression
applique [e]      = e
applique [e1, e2] = App e1 e2
applique es       = App (applique (init es)) (last es)

applique' :: [Expression] -> Expression
applique' = foldl1 App


-- plusieurs Var sous forme de App
exprsP :: Parser Expression
exprsP = do exprs <- some exprP
            pure (applique exprs)

-- une Lam
lambdaP :: Parser Expression
lambdaP = do chaine "\\" <|> chaine "λ"
             espacesP
             lam <- nomP
             chaine "-> "
             exprs <- exprsP
             pure (Lam lam exprs)

-- une Lam ou une Var
exprP :: Parser Expression
exprP = do lambdaP <|> exprParentheseeP <|> nombreP <|> booleenP <|> varP


-- des Var ou des Lam entre parantheses et consomme les espaces apres
exprParentheseeP :: Parser Expression
exprParentheseeP = do car '('
                      res <- exprsP <|> exprP
                      car ')'
                      espacesP
                      pure res

stringToDigit :: String -> Int
stringToDigit xs = read xs -- char to digit

nombreP :: Parser Expression
nombreP = do cs <- some (carQuand isDigit)
             espacesP
             pure(Lit (Entier (read cs)))


booleenP :: Parser Expression
booleenP = do cs <- (chaine "True" <|> chaine "False")
              espacesP
              pure(Lit (Bool (read cs)))

expressionP :: Parser Expression
expressionP = do espacesP
                 exprsP

ras :: String -> Expression
ras cs = case (runParser expressionP cs) of
              Nothing   -> error "Erreur d’analyse syntaxique"
              Just(r,_) -> r

data ValeurA = VLitteralA Litteral
             | VFonctionA (ValeurA -> ValeurA)


instance Show ValeurA where
   show (VFonctionA _) = "λ"
   show (VLitteralA (Entier e)) = show e
   show (VLitteralA (Bool b)) = show b

type Environnement a = [(Nom, a)]

interpreteA :: Environnement ValeurA -> Expression -> ValeurA
interpreteA _   (Lit l)          = VLitteralA l
interpreteA env (Lam n e)        = VFonctionA (\x -> interpreteA ((n, x):env) e)
interpreteA env (Var n)          = case lookup n env of
                                        Nothing -> error "variable is not in environnement"
                                        Just(v) -> v
interpreteA env (App e1 e2)      = f (interpreteA env e2)
  where f = case interpreteA env e1 of
                  (VLitteralA _) -> error "first expression is not a function"
                  (VFonctionA r) -> r

getInteger :: ValeurA -> Integer
getInteger (VLitteralA (Entier i)) = i
getInteger _                       = error "cannot get integer from non litteral"

negA :: ValeurA
negA = VFonctionA (\x -> VLitteralA (Entier (-getInteger x)))

addA :: ValeurA
addA = VFonctionA f
       where f (VLitteralA (Entier x)) = VFonctionA g
                    where g (VLitteralA (Entier y)) = VLitteralA (Entier (x + y))
                          g e = error ("Expected an Integer, received, \"" ++ (show e))
             f e = error ("Expected an Integer, received, \"" ++ (show e))

releveBinOpEntierA :: (Integer -> Integer -> Integer) -> ValeurA
releveBinOpEntierA op = VFonctionA f
       where f (VLitteralA (Entier x)) = VFonctionA g
                    where g (VLitteralA (Entier y)) = VLitteralA (Entier (op x y))
                          g e = error ("Expected an Integer, received, \"" ++ (show e))
             f e = error ("Expected an Integer, received, \"" ++ (show e))

envA :: Environnement ValeurA
envA = [ ("neg",   negA)
       , ("add",   releveBinOpEntierA (+))
       , ("soust", releveBinOpEntierA (-))
       , ("mult",  releveBinOpEntierA (*))
       , ("quot",  releveBinOpEntierA quot)
       , ("if", ifthenelseA) ]


getBool :: ValeurA -> Bool
getBool (VLitteralA (Bool b)) = b
getBool _                        = error "cannot get boolean from a non litteral"

ifthenelseA :: ValeurA
ifthenelseA = VFonctionA (\cond -> VFonctionA
                                  (\res_true ->
                                      VFonctionA
                                      (\res_false -> if getBool cond
                                                     then res_true
                                                     else res_false)
                                      )
                         )


-------------- Interprete avec erreurs --------------

data ValeurB = VLitteralB Litteral
            | VFonctionB (ValeurB -> ErrValB)

type MsgErreur = String
type ErrValB   = Either MsgErreur ValeurB

instance Show ValeurB where
   show (VFonctionB _)          = "λ"
   show (VLitteralB (Entier n)) = show n
   show (VLitteralB (Bool n))   = show n


messErrVarNonDef :: Nom -> MsgErreur
messErrVarNonDef n =  "la variable " ++ n ++ " not defined"

messErrAppLitLeft :: ValeurB -> MsgErreur
messErrAppLitLeft l =  (show l) ++ " n'est pas une fonction, application impossible"

messErrInteger :: ValeurB -> MsgErreur
messErrInteger l = (show l) ++ "n'est pas un entier"

messErrDivZero :: MsgErreur
messErrDivZero = "division par zero"

interpreteB :: Environnement ValeurB -> Expression -> ErrValB
interpreteB _   (Lit l)          = Right (VLitteralB l)
interpreteB env (Lam n e)        = Right (VFonctionB (\x -> interpreteB ((n, x):env) e))
interpreteB env (Var n)          = case lookup n env of
                                        Nothing -> Left n
                                        Just(v) -> Right v
interpreteB env (App x y) = case interpreteB env x of
                                    Left e               -> Left (messErrVarNonDef e)
                                    Right (VFonctionB f) -> case (interpreteB env y) of
                                                                    Left e  ->  Left (messErrVarNonDef e)
                                                                    Right v -> f v

addB :: ValeurB
addB =  VFonctionB f
       where f   (VLitteralB (Entier x)) = Right (VFonctionB g)
                    where g (VLitteralB (Entier y)) = Right (VLitteralB (Entier (x + y)))
                          g e = Left (messErrInteger e)
             f e = Left (messErrInteger e)

quotB :: ValeurB
quotB =  VFonctionB f
      where f   (VLitteralB (Entier x)) = Right (VFonctionB g)
                   where g (VLitteralB (Entier y)) = case y of
                                                      0 -> Left (messErrDivZero)
                                                      e -> Right (VLitteralB (Entier (x `quot` e)))
                         g e = Left (messErrInteger e)
            f e = Left (messErrInteger e)

-------------- Interprete traçant --------------

data ValeurC = VLitteralC Litteral
             | VFonctionC (ValeurC -> OutValC)

type Trace   = String
type OutValC = (Trace, ValeurC)

instance Show ValeurC where
    show (VFonctionC _)          = "λ"
    show (VLitteralC (Entier n)) = show n
    show (VLitteralC (Bool n))   = show n

interpreteC :: Environnement ValeurC -> Expression -> OutValC
interpreteC _   (Lit l)          = ("",VLitteralC l)
interpreteC env (Lam n e)        = ("",(VFonctionC (\x -> interpreteC ((n, x):env) e)))
interpreteC env (Var n)          = ("", fromJust (lookup n env))
interpreteC env (App x y) = case interpreteC env x of
                                    (t, (VFonctionC f)) -> ((t++"."++(fst application)), snd application)
                                                            where application = f (snd (interpreteC env y))
                                    e                   -> error (show e)

pingC :: ValeurC
pingC = VFonctionC (\x -> ("p", x))


main :: IO ()
main = do putStr "minilang> "
          hFlush stdout
          fin <- isEOF
          if fin
              then putStrLn "bye"
              else do cmd <- getLine
                      print (interpreteA envA (ras cmd))
                      main
