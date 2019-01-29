
notastercerestudiante([_|[_|[H|_]]],X):- X = H.

tamano([],0).
tamano([_|T],X) :- tamano(T,XX), X is XX+1.
suma([],0).
suma([H|T],S):- suma(T,SS), S is SS+H. 
prom(L,P) :- suma(L,S) , tamano(L,X) , P is S/X.

tamanocurso([],0).
tamanocurso([H|T],X) :- tamanocurso(T,XX), tamano(H,XXX),X is XX+XXX.

sumacurso([],0).
sumacurso([H|T],S):- sumacurso(T,SS), suma(H,SSS) ,S is SS+SSS. 

promcurso(L,P):- sumacurso(L,S), tamanocurso(L,X), P is S/X.
    