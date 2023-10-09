# Projet Remise 1

## Divers

- Git : gitignore en ordre
- Readme : ajouter la commande maven pour exécuter le programme
- Javadoc : en ordre
- Couverture de test : couverture suffisante

## Analyse de code

Commence par jeter un œil aux remarques générées par Intelliji dans la page index.html

Reviens-vers moi si il y a des remarques que tu ne comprends pas ou que tu ne trouves pas appropriées

## Controller

La logique est la suivante : 

1. `game` possède un tableau d'entiers
2. le contrôleur demande une copie de ce tableau
3. `game` fait évoluer le contenu de son tableau d'entiers
4. le contrôleur demande à `game` de comparer le nouveau tableau avec la copie de l'étape 2

Ce va et vient n'a pas de sens.

1. `game` possède un tableau d'entiers
2. `game` possède un attribut booléen qui vaut false par défaut
3. `gam`e fait évoluer le contenu de son tableau d'entiers et met le booléen à `True` si le tableau change

En résumé le contrôleur n'a jamais besoin d'avoir la copie du tableau.

## View

La gestion des entrées via la classe `Scanner` doit se faire dans la vue et non dans le contrôleur.

Une instruction qui contient dépend d'une constante n'a pas de sens ici : `if (score == 2048)`

La vue devrait recevoir un booléen vaut `True` si le jeu est gagné.

## Model

- Facade : en ordre
- Gestion du tableau de jeu : 
  - la visibilité `public` est-elle nécessaire ?
  - méthode `moveUp`, `moveDown`, `moveRight`, `moveLeft` identique. On peut les fusionner. 
    - La différences semble résider dans `destRow++` qui devient `destRow--` ou `destCol--` ou `destCol++`
    - On peut imaginer que cette mise à jour devienne `destRow = Direction.next(destRow)` et `destCol = Direction.next(destCol)`
  - le même travail eut s'appliquer pour les méthodes clean par exemple
- Gestion du jeu, la classe `Game` : 
  - l'accesseur de `Board` doit retourner une copie
  - `magicNumber` : on peut écrire toute la méthode via une expression lambda
  - la méthode `copy` fait-elle doublon avec la méthode `Board.getNumbers()` ?

## Méthodes et paramètres d'entrées

Attention les méthodes qui utilisent directement leurs paramètres d'entrées, doivent contrôler leurs validités, elles en sont responsables. 





