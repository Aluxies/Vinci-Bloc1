# Projet web 2023 - Groupe XX

Ce repository GitHub reprend le boilerplate du code de votre projet web, ou la base à partir de laquelle vous allez déveloper.

## Apprentissage de Git et GitHub

Suivez le document proposé sur moodle pour apprendre à utiliser Git et GitHub durant votre projet.

## Préparation du projet

Quand vous clonez ce repository sur votre machine, vous devez le préparer pour qu'il soit utilisable. 

Si vous n'avez pas encore installé la base de données sur cette machine, créez là en utilisant le script de (ré)création de base de données que vous avez développé. Mettez le fichier SQLITE de la base de données dans le dossier `data`.

Copiez le fichier `config.js.example` avec le nom `config.js`, et modifiez le pour remplacer le secret par une valeur sécurisée. Remplacez également le chemin du fichier de base de données par le bon chemin de l'endroit où vous avez installé votre base de données.

Pour utiliser les informations sauvegardées dans le fichier `config.js`, vous pouvez faire comme suit :

```js
const config = require('./path/to/config.js');

console.log(config.dbPath); // affiche le chemin du fichier de base de données
```

## Lancer le projet

Une fois que le projet est préparé tel qu'expliqué dans la section précédente, vous pouvez le lancer avec les commandes suivantes dans un terminal ouvert à la racine du projet :

```sh
npm install
npm start
```
