# Wargame
---
## Constitution du groupe
- DELFORCE Pierre 
- MAILLARD Tom 
- MARETTE Gauthier

## Répartition du travail
- Conception : MAILLARD Tom, MARETTE Gauthier  
- Développement : DELFORCE Pierre, MAILLARD Tom, MARETTE Gauthier
- Documentation : DELFORCE Pierre, MAILLARD Tom

## Choix de conceptions
1. **Abstact Factory** - Génération des cases du wargame
> Le choix d'utiliser une abstract factory (cf annexe 2) a été réalisé afin de suivre le plus possible le concept de responsabilité unique des classes. Cette approche permet aussi des évolutions simples du système, par exemple l'ajout d'autres types de terrains pour les cas. 

2. Génération des voisinnages
>Sans suivre un patron de conception bien précis, on suit néanmoins le principe ouvert/fermé pour la gestion de notre voisinnage, la classe de "base" - TileNeighbors - est fermée à la modification, comporte la méthode applicable à tous types de voisinnage. Néanmoins elle est ouverte à l'extension dans la mesure où ses classes filles ont leurs propres façons de construire un voisinnage.

3. **Itérateurs**
>Notre choix ici a été d'utiliser des structures for-each afin d'itérer sur les diverses listes - ArrayList - notamment qu'on a créé au sein des différentes classes, jugeant qu'il n'était pas nécéssaire d'implémenter des Iterators faits nous-mêmes là où les options fournies par Java étaient en pleine adéquation avec ce qu'on voulait faire avec nos listes et simple d'implémentation 

4. L'utilisation de variables statiques
>Concrètement, ce choix est un très mauvais choix de notre part dans le cadre de la POO, et nous en sommes conscients. La majorité des variables statiques utilisées servent à suivre l'avancée du jeu - le prochain joueur devant jouer, la prochaine armée devant être placée, la représentation interne (une Map) des cases du jeu et d'autres détails comme les dimensions de la carte de jeu. Le choix d'employer lourdement a été réalisé afin de pallier aux défauts de notre conception et fournir un jeu fonctionnel à 100%

5. La fonction Tile.placeArmy(Army army)
> Pareil que précédemment, cette fonction gère quasi-intégralement la progression du jeu alors que son rôle primaire est de placer une armée sur une case. Un choix réalisé une fois encore afin de pouvoir rendre un travail fonctionnel à défaut d'être un mauvais choix de conception dans l'absolu

## Réflexions personelles
> Un de nos plus gros problèmes durant ce TP a été la gestion d'interface graphique, et plus notablement, les interactions entre les éléments Swing et le restant de l'application. Comme mentionné plus haut, notre choix a été d'utiliser des variables "globales" pour assurer une bonne progression du jeu lors de l'exécution. 
Néanmoins un choix plus intelligent pous nous aurait été d'employer un modèle MVC, avec un Modèle qui gère les aspects du jeu, les Contrôleurs qui mettent à jour le modèle en fonction des actions réalisées sur la Vue, et finalement la Vue qui affiche le jeu en lui-même. L'option a été considérée pendant la conception et le développement mais mise de côté dans la mesure où on ne parvenait pas à des résultats concluents avec nos premières tentatives.