import Parser
import Data.Char

type Nom = String

data Expression = Lam Nom Expression
                | App Expression Expression
                | Var Nom
                | Lit Litteral
                deriving (Show,Eq)

data Litteral = Entier Integer
              | Bool   Bool
              deriving (Show,Eq)


espacesP :: Parser ()
espacesP = do many (car ' ')
              pure (())

nomP :: Parser Nom
nomP = do res <- some (carQuand isAlpha)
          espacesP
          pure (res)

varP :: Parser Expression
varP = do res <- nomP
          pure (Var res)

applique :: [Expression] -> Expression
applique [e]      = e
applique [e1, e2] = App e1 e2
applique es       = App (applique (init es)) (last es)

applique' :: [Expression] -> Expression
applique' = foldl1 App

exprP :: Parser Expression
exprP = varP


exprsP :: Parser Expression
exprsP = do exprs <- some exprP
            pure (applique exprs)

lambdaP :: Parser Expression
lambdaP = do chaine "\\"
             espacesP
             lam <- nomP
             chaine "-> "
             exprs <- exprsP
             pure (Lam lam exprs)

exprP' :: Parser Expression
exprP' = do exprP <|> lambdaP





--main :: IO ()
--main = _
