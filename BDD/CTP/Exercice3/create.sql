drop table if exists Offre;
drop table if exists Groupe;
drop table if exists Replay;

CREATE TABLE Offre(
oid int primary key,
onom varchar(30),
ofournisseur varchar(30),
prix FLOAT);

CREATE TABLE Groupe(
gid int primary key,
oid int references Offre(gid),
gnom varchar(30),
acc varchar(30));

CREATE TABLE Replay(
rid int primary key,
gid int references Groupe(gid),
rnom varchar(30),
rtype varchar(30),
cat varchar(30));
