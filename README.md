# CS Games 2019 - Compétition mobile

Bienvenue à la **compétition mobile des CS Games 2019**!

[(English below ↓)](#cs-games-2019---mobile-competition)

## Introduction

À bord du CS Paradise, les festivités s'enflamment. Cocktail en main et téléphone en poche, vous vous joignez à la fête et tentez de vous y intégrer. Vous réalisez cependant que tout le monde semble utiliser un appareil spécial pour commander des breuvages, que vous ne possédez pas. Vous profitez donc de votre propre appareil et de vos capacités de développement mobile pour répliquer cet appareil et rejoindre le serveur, qui pourra alors vous abreuver de délicieux nectars.

<p align="center">***</p>

Après quelques recherches dans les confins de la toile, vous arrivez à dénicher une version de développement d'une application utilisée par d'autres étudiants. Affectueusement appelée **MixParadise**, elle permet de créer son breuvage de rêve en assemblant des jus, liqueurs et ingrédients dans un mélangeur. Celle-ci n’est pas tout à fait complète, mais semble très prometteuse pour débuter.

<table>
<thead><tr><th colspan="4">MixParadise</th></tr></thead>
<tbody><tr>
<td><a href="https://user-images.githubusercontent.com/4378424/54859859-93f5e480-4ce8-11e9-93c7-8ace202e5810.png" target="_blank"><img src="https://user-images.githubusercontent.com/4378424/54859859-93f5e480-4ce8-11e9-93c7-8ace202e5810.png" alt="SPLASH"></a></td>
<td><a href="https://user-images.githubusercontent.com/4378424/54859822-2fd32080-4ce8-11e9-8efb-59ca3d9b7fff.png" target="_blank"><img src="https://user-images.githubusercontent.com/4378424/54859822-2fd32080-4ce8-11e9-8efb-59ca3d9b7fff.png" alt="LIST"></a></td>
<td><a href="https://user-images.githubusercontent.com/4378424/54859824-2fd32080-4ce8-11e9-902a-6b992ac95823.png" target="_blank"><img src="https://user-images.githubusercontent.com/4378424/54859824-2fd32080-4ce8-11e9-902a-6b992ac95823.png" alt="MAP"></a></td>
<td><a href="https://user-images.githubusercontent.com/4378424/54859823-2fd32080-4ce8-11e9-9858-23ab2b950639.png" target="_blank"><img src="https://user-images.githubusercontent.com/4378424/54859823-2fd32080-4ce8-11e9-9858-23ab2b950639.png" alt="AR"></a></td>
</tr>
<tr>
<td><em>Écran d'accueil</em></td>
<td><em>Mélangeur</em></td>
<td><em>Ingrédients</em></td>
<td><em>Évaluation</em></td>
</tr>
</tbody>
</table>

Une [photo un peu floue](https://user-images.githubusercontent.com/4378424/54860826-1f29a700-4cf6-11e9-8ea8-8f3ae0395902.jpeg) qui accompagnait l'archive vous permet même d’obtenir la documentation de l’API du serveur, qui a été retranscrite dans le dossier [`/api`](https://github.com/mirego/csgames19-competition/tree/master/api) de ce répertoire. Vous constatez que le serveur du CS Paradise est très prolifique, il permet entre autres de:

* Lister les ingrédients disponibles pour assembler votre breuvage de choix
* Transmettre une recette au serveur afin qu’elle soit préparée pour vous
* Recevoir un taux d’appréciation du serveur face à la recette que vous avez demandée

Avec le projet de départ et la documentation de l'API, vous vous retroussez les manches, ouvrez votre IDE et vous lancez à la quête du cocktail parfait. 🍹


## Pour débuter

Vous trouverez le code de base du projet **MixParadise** dans les sous-dossiers [`/ios`](https://github.com/mirego/csgames19-competition/tree/master/ios) et [`/android`](https://github.com/mirego/csgames19-competition/tree/master/android) de ce répertoire GitHub:

<table width="100%">
  <thead><tr><th colspan="2">Applications mobiles</th></tr></thead>
  <tbody>
    <tr>
      <td align="center"><a href="https://github.com/mirego/csgames19-competition/tree/master/ios" target="_blank"><br><img src="https://cloud.githubusercontent.com/assets/4378424/13625721/90d6d7de-e588-11e5-83d9-b16f14b6cfaa.png" height="100"><br><br>Application iOS</a></td>
      <td align="center"><a href="https://github.com/mirego/csgames19-competition/tree/master/android" target="_blank"><br><img src="https://cloud.githubusercontent.com/assets/4378424/13625718/90ca7e30-e588-11e5-9cd1-7fcc06d4a62a.png" height="100"><br><br>Application Android</a></td>
    </tr>
  </tbody>
</table>

Toutes les instructions pour démarrer les applications de base devraient être couvertes au bout des liens respectifs. Si vous avez besoin d'assistance, n'hésitez pas à nous en faire part.


## Le défi

L'application **MixParadise** est construite pour ajouter des ingrédients à un mélangeur, les combiner en un breuvage créatif, puis les envoyer au serveur pour être servi et validé. Le défi consiste à compléter cette application en quête du cocktail parfait, suivant l'une ou plusieurs des 3 pistes ci-bas.

### 🛠 Fonctionnalités listées

Les lignes suivantes détaillent l'ensemble des fonctionnalités prévues ainsi que leur état de complétion:

<table width="100%"><thead><tr><th>Section</th><th>Fonctionnalité</th><th>Complétée</th></tr></thead><tbody>
  <tr> <td><b>Structure</b></td>
       <td>Écran d'accueil</td>
       <td align="center">✅</td> </tr>
  <tr> <td> </td>
       <td>Fond d'écran et base du mélangeur</td>
       <td align="center">✅</td> </tr>
  <tr> <td> </td>
       <td>Boutons d'action “Add Ingredients” et “Serve”</td>
       <td align="center">✅</td> </tr>
  <tr> <td> </td>
       <td>Bouton d'action du mélangeur</td>
       <td align="center">✅</td> </tr>
  <tr> <td><b>Add Ingredients</b></td>
       <td>Fenêtre modale pour ajouter des ingrédients<br><small><em>S'ouvre à l'appui du bouton "Add Ingredients"</em></small></td>
       <td align="center">✅</td> </tr>
  <tr> <td> </td>
       <td>Chargement des ingrédients du serveur<br><small><em>Se référer à la <a href="https://github.com/mirego/csgames19-competition/tree/master/api">documentation de l'API</a> pour récupérer la liste d'ingrédients</em></small></td>
       <td align="center">❌</td> </tr>
  <tr> <td> </td>
       <td>Chargement des ingrédients secrets du serveur<br><small><em>Les instructions sont aussi disponibles dans la <a href="https://github.com/mirego/csgames19-competition/tree/master/api">documentation de l'API</a></em></small></td>
       <td align="center">❌</td> </tr>
  <tr> <td> </td>
       <td>Affichage de la liste des ingrédients<br><small><em>Référez-vous à la <a href="https://user-images.githubusercontent.com/4378424/54859824-2fd32080-4ce8-11e9-902a-6b992ac95823.png">maquette graphique</a> pour inspiration visuelle</em></small></td>
       <td align="center">❌</td> </tr>
  <tr> <td> </td>
       <td>Appui d’un ingrédient pour l’ajouter<br><small><em>Devraient apparaître dans le mélangeur suite à l'appui</em></small></td>
       <td align="center">❌</td> </tr>
  <tr> <td> </td>
       <td>Ajout d’un ingrédient "liquide" au mélangeur<br><small><em>Les ingrédients apparaissent un par-dessus avec leurs couleurs respectives</em></small></td>
       <td align="center">✅</td> </tr>
  <tr> <td> </td>
       <td>Ajout d’un ingrédient "solide" au mélangeur<br><small><em>Les ingrédients apparaissent dans le liquide (si déjà présent)</em></small></td>
       <td align="center">✅</td> </tr>
  <tr> <td> </td>
       <td>Affichage du nombre d'ingrédients ajoutés<br><small><em>Nombre indiqué dans la fenêtre d'ingrédients suite aux ajouts au mélangeur</em></small></td>
       <td align="center">❌</td> </tr>
  <tr> <td><b>Push</b></td>
       <td>Mélange des ingrédients à l'appui du bouton<br><small><em>Mélange des liquides entre eux pour former un cocktail dans le mélangeur</em></small></td>
       <td align="center">✅</td> </tr>
  <tr> <td> </td>
       <td>Couleur résultante du liquide<br><small><em>Mélange des couleurs des ingrédients liquides selon leurs proportions</em></small></td>
       <td align="center">❌</td> </tr>
  <tr> <td><b>Serve<b></td>
       <td>Fenêtre modale pour voir les résultats<br><small><em>S'ouvre à l'appui du bouton "Serve" lorsque le mélange est complété</em></small></td>
       <td align="center">✅</td> </tr>
  <tr> <td> </td>
       <td>Chargement des résultats du serveur<br><small><em>Référez-vous à la <a href="https://github.com/mirego/csgames19-competition/tree/master/api">documentation de l'API</a> pour la requête et la réponse</em></small></td>
       <td align="center">❌</td> </tr>
  <tr> <td> </td>
       <td>Affichage des résultats de la recette<br><small><em>Référez-vous à la <a href="https://user-images.githubusercontent.com/4378424/54859823-2fd32080-4ce8-11e9-9858-23ab2b950639.png">maquette graphique</a> pour inspiration visuelle</em></small></td>
       <td align="center">✅</td> </tr>
  <tr> <td> </td>
       <td>Animation de succès<br><small><em>Lorsque la recette est évaluée à 100% 🎉</em></small></td>
       <td align="center">✅</td> </tr>
  <tr> <td> </td>
       <td>Nouveau breuvage<br><small><em>Nettoyage du mélangeur afin de recommencer suite au service d'un breuvage</em></small></td>
       <td align="center">✅</td> </tr>
</tr></tbody></table>

Le défi ici consiste à remplir les trous fonctionnels pour arriver à l'application complète telle que développée dans sa version finale. Les fonctionnalités peuvent être développées de la façon de votre choix, mais la structure existante de l'application devrait vous servir de guide. 

Chaque fonctionnalité à compléter possède une valeur égale de **10 pts**, pour un total de **70 pts** sur cette piste.

### 💡 Fonctionnalités créatives

Si tout va bien ou que vous souhaitez essayer autre chose, vous avez aussi l'opportunité d'innover pour accumuler des points.

- Inventez une nouvelle fonctionnalité à l'application qui n'est pas listée ci-haut.
- Trouvez une fonctionnalité innovante qui créerait une valeur ajoutée à l'application et implantez-la de façon à nous impressionner.

Cette piste peut vous rapporter jusqu'à **10 pts**.

### 🍹 Recette parfaite

En transmettant les recettes au serveur, il vous attribuera une évaluation basée sur différents critères.

- Si vous arrivez à trouver la combinaison parfaite des ingrédients pour obtenir un résultat de 100%, spécifiez-la au moment dans vos notes de soumission.
- Attention de ne pas bombarder le serveur de requêtes – il s'agit d'un serveur partagé avec les autres participants de la compétition, agissez en bons citoyens avec vos confrères.

Cette piste peut vous rapporter jusqu'à **10 pts**.


## Évaluation

L'objectif n'est pas nécessairement de compléter **tous** les éléments ci-haut dans une période de 3 heures. Cela dit, chaque item possède la même valeur en points, mais ils ne présentent pas tous la même complexité. Il est donc votre responsabilité de les prioriser et de gérer votre temps en conséquence, afin de maximiser votre succès.

### Critères

La grille suivante sera utilisée pour la correction:

<table><thead><tr><th>Critères</th><th>Points</th></tr></thead>
  <tbody><tr><td>
        <p><strong>🛠 Fonctionnalités listées (×7)</strong></p>
        <ul>
          <li>Fonctionnalité complétée et conforme</li>          <li>Qualité du développement (code et propreté)</li>          <li>Qualité de l'intégration (apparence et utilisation)</li>
          <li>Respect des maquettes visuelles (si applicable)</li>
        </ul>
      </td><td align="right" valign="top"><strong>/ 70</td></tr><tr><td>
        <p><strong>💡 Fonctionnalités créatives</strong></p>
        <ul>
          <li>Originalité et innovation</li>          <li>Qualité du développement (code et propreté)</li>          <li>Qualité de l'intégration (apparence et utilisation)</li>
        </ul>
      </td>
      <td align="right" valign="top"><strong>/ 10</strong></td>
    </tr>
    <tr>
      <td>
        <p><strong>🍹 Recette parfaite</strong></p>
        <ul>
          <li>Liste des ingrédients et quantités exactes</li>          <li>Résultat de 100% validé avec le serveur</li>
        </ul>
      </td>
      <td align="right" valign="top"><strong>/ 10</strong></td>
    </tr>
    <tr>
      <td>
        <p><strong>📂 Remise</strong></p>
        <ul>
          <li>"Pull Request" en bonne et due forme</li>
          <li>Description fournie et bien documentée</li>
        </ul>
      </td>
      <td align="right" valign="top"><strong>/ 10</strong></td>
    </tr>
  </tbody>
  <tfoot>
    <tr>
      <td><strong>Total</strong></td>
      <td align="right"><strong>/ 100</strong></td>
    </tr>
  </tfoot>
</table>

Autres points importants:

- Un projet qui ne **builde pas**, en suite d'efforts minimaux de notre part, se verra pénalisé de **-50 pts**.

- Votre application doit partir de nos projets de base et être soumis à l'aide d'une Pull Request propre, sans quoi vous serez pénialisés de **-25 pts**.

- L'heure de soumission de votre code ne doit pas dépasser la fin de la compétition. Une tricherie identifiée recevra une note de **0 / 100**.

### Remise

<table><thead><tr><th align="center">
⚠️ Prévoyez au moins 10 minutes avant la fin de l'épreuve pour préparer votre soumission ⚠️ 
</th></tr></thead></table>

La correction sera effectuée à l'aide de **Pull Requests** sur le répertoire GitHub.

> Si vous n'êtes pas familiers avec ce concept, référez-vous à ces articles: 
> - [Fork a repo](https://help.github.com/articles/fork-a-repo/)
> - [Creating a pull request from a fork](https://help.github.com/articles/creating-a-pull-request-from-a-fork/).

Votre **Pull Request** doit respecter la structure suivante:

- **Titre**: Nom de votre équipe (exemple: `DaStreez`)
- **Description**: Utiliser le **template Markdown** ci-bas en remplaçant les textes entre `{}`


```markdown
## Équipe

**Nom de l'équipe:** {DaStreez}
**Numéro de l'équipe:** {1}
**Délégation:** {Université de Griffintown}

**Auteurs:**
- {Hugo Lefrançois}
- {Mathieu Larue}

## Solution

**Plateforme:** {iOS | Android}

**Fonctionnalités:**

| Fonctionnalité                                  | Complété |
| ----------------------------------------------- |:--------:|
| Chargement des ingrédients du serveur           | ✔        |
| Chargement des ingrédients secrets du serveur   | {?}      |
| Affichage de la liste des ingrédients           | {?}      |
| Appui d’un ingrédient pour l’ajouter            | {?}      |
| Affichage du nombre d'ingrédients ajoutés       | {?}      |
| Couleur résultante du liquide                   | {?}      |
| Chargement des résultats du serveur             | {?}      |
| {Nouvelle fonctionnalité}                       | {?}      |

**Votre meilleure recette:**

- {...}

**Comment la compétition s'est passée:**

- {...}
- {...}

**Fiertés et déceptions:**

- {...}
- {...}

```

> **NOTE:** Si vous n'avez pas de compte GitHub, levez la main au début de la compétition, nous pourrons vous fournir un compte générique.

## Remerciements

Merci aux membres de l'équipe de Mirego derrière cette compétition:

- [Émile Bélair](https://www.linkedin.com/in/emilebelair/) (responsable de compétition)
- [Hugo Lefrançois](https://www.linkedin.com/in/hugo-lefran%C3%A7ois-7419241/) (développeur principal)
- [Olivier Pineau](https://www.linkedin.com/in/olivierpineau/) (développeur iOS)
- [Mathieu Larue](https://www.linkedin.com/in/mathieu-larue-871055a6/) (développeur Android)
- [Olivier Aubin](https://www.linkedin.com/in/olivier-aubin-40678632/) (designer UI)
- [Claude Cormier](https://www.linkedin.com/in/claudecormier/) (culture et bonheur)

## Licence

Cette compétition est © 2019 [Mirego](http://www.mirego.com) et peut être librement distribuée sous la [license BSD](http://opensource.org/licenses/BSD-3-Clause).
Voir le fichier [`LICENSE.md`](https://github.com/mirego/csgames19-competition/blob/master/LICENSE.md).

## À propos de Mirego

[Mirego](http://mirego.com) est une équipe de gens passionnés qui croit que le travail est un lieu agréable propice à l’innovation. Nous sommes une équipe de [personnes talentueuses](http://life.mirego.com) qui construisent des applications Web et Mobile. Nous nous réunissons pour partager nos idées et [changer le monde](http://mirego.org).

Nous aimons aussi les [logiciels open-source](http://open.mirego.com) et tentons de redonner le plus possible dans la communauté.

---

# CS Games 2019 - Mobile Competition

Welcome to the **CS Games 2019 Mobile Competition**!

[(French above ↑)](#cs-games-2019---compétition-mobile)

## Introduction

Aboard the CS Paradise, festivities are on fire. Cocktail in your hand and phone in your pocket, you join the party and hope to blend in. You realize that most people seem to use some sophisticated device to order beverages, which you don't have. You decide to use your own device and mobile development skills to replicate this device and reach the server, who will then replenish yourself with delicious nectars.

<p align="center">***</p>

After some deep search in the dark web, you achieve to dig out a development version of an app used by other students. Affectionately called **MixParadise**, the app allows to create your dream drink by assembling juices, soft drinks and ingredients in a mixer. It is not fully complete, but it is very promising to start with.

<table>
<thead><tr><th colspan="4">MixParadise</th></tr></thead>
<tbody><tr>
<td><a href="https://user-images.githubusercontent.com/4378424/54859859-93f5e480-4ce8-11e9-93c7-8ace202e5810.png" target="_blank"><img src="https://user-images.githubusercontent.com/4378424/54859859-93f5e480-4ce8-11e9-93c7-8ace202e5810.png" alt="SPLASH"></a></td>
<td><a href="https://user-images.githubusercontent.com/4378424/54859822-2fd32080-4ce8-11e9-8efb-59ca3d9b7fff.png" target="_blank"><img src="https://user-images.githubusercontent.com/4378424/54859822-2fd32080-4ce8-11e9-8efb-59ca3d9b7fff.png" alt="LIST"></a></td>
<td><a href="https://user-images.githubusercontent.com/4378424/54859824-2fd32080-4ce8-11e9-902a-6b992ac95823.png" target="_blank"><img src="https://user-images.githubusercontent.com/4378424/54859824-2fd32080-4ce8-11e9-902a-6b992ac95823.png" alt="MAP"></a></td>
<td><a href="https://user-images.githubusercontent.com/4378424/54859823-2fd32080-4ce8-11e9-9858-23ab2b950639.png" target="_blank"><img src="https://user-images.githubusercontent.com/4378424/54859823-2fd32080-4ce8-11e9-9858-23ab2b950639.png" alt="AR"></a></td>
</tr>
<tr>
<td><em>Splash screen</em></td>
<td><em>Mixer</em></td>
<td><em>Ingredients</em></td>
<td><em>Rating</em></td>
</tr>
</tbody>
</table>

A [blurry picture](https://user-images.githubusercontent.com/4378424/54860826-1f29a700-4cf6-11e9-8ea8-8f3ae0395902.jpeg) found with the code gives you some insights on the server API documentation, which was transcribed in the [`/api`](https://github.com/mirego/csgames19-competition/tree/master/api) folder of this repository. You'll find that the CS Paradise server is very prolific, as he is able to:

* List available ingredients to assemble the drink of your choice
* Submit a recipe to the server to have it prepared for you
* Receive an appreciation note from the server for your recipe

With the base project and the API documentation, you roll up your sleeves, open your IDE and start your quest for the perfect cocktail. 🍹


## To Begin

You will find the base code of **MixParadise** in the subfolders [`/ios`](https://github.com/mirego/csgames19-competition/tree/master/ios) and [`/android`](https://github.com/mirego/csgames19-competition/tree/master/android) of this GitHub repository:

<table width="100%">
  <thead>
    <tr>
      <th colspan="2">Applications mobiles</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td align="center"><a href="https://github.com/mirego/csgames19-competition/tree/master/ios" target="_blank"><br><img src="https://cloud.githubusercontent.com/assets/4378424/13625721/90d6d7de-e588-11e5-83d9-b16f14b6cfaa.png" height="100"><br><br>iOS Application </a></td>
      <td align="center"><a href="https://github.com/mirego/csgames19-competition/tree/master/android" target="_blank"><br><img src="https://cloud.githubusercontent.com/assets/4378424/13625718/90ca7e30-e588-11e5-9cd1-7fcc06d4a62a.png" height="100"><br><br>Android Application</a></td>
    </tr>
  </tbody>
</table>

All the instructions to build these base projects should be covered in the above links. If you need any assistance, please reach out to us.

## The Challenge

The **MixParadise** app is built to add ingredients in a mixer, combine them in a creative beverage, then send them to a server to be served and validated. The challenge consists to complete this app to find the perfect cocktail, following one or many of the 3 tracks below.

### 🛠 Listed Features

The following lines detail all the features planned for the app, with their state of completion:

<table width="100%"><thead><tr><th>Section</th><th>Feature</th><th>Completion</th></tr></thead><tbody>
  <tr> <td><b>Structure</b></td>
       <td>Splash screen</td>
       <td align="center">✅</td> </tr>
  <tr> <td> </td>
       <td>Backdrop and mixer base</td>
       <td align="center">✅</td> </tr>
  <tr> <td> </td>
       <td>“Add Ingredients” and “Serve” action buttons</td>
       <td align="center">✅</td> </tr>
  <tr> <td> </td>
       <td>Mixer action button</td>
       <td align="center">✅</td> </tr>
  <tr> <td><b>Add Ingredients</b></td>
       <td>Modal screen to add ingredients<br><small><em>Opens when tapping the button "Add Ingredients"</em></small></td>
       <td align="center">✅</td> </tr>
  <tr> <td> </td>
       <td>Fetch ingredients from the server<br><small><em>Refer to the <a href="https://github.com/mirego/csgames19-competition/tree/master/api">API documentation</a> to fetch the ingredients list</em></small></td>
       <td align="center">❌</td> </tr>
  <tr> <td> </td>
       <td>Fetch secret ingredients from the server<br><small><em>Instructions are also available in the <a href="https://github.com/mirego/csgames19-competition/tree/master/api">API documentation</a></em></small></td>
       <td align="center">❌</td> </tr>
  <tr> <td> </td>
       <td>Show the ingredients list<br><small><em>Refer to the <a href="https://user-images.githubusercontent.com/4378424/54859824-2fd32080-4ce8-11e9-902a-6b992ac95823.png">design mockup</a> for visual inspiration</em></small></td>
       <td align="center">❌</td> </tr>
  <tr> <td> </td>
       <td>Tap on an ingredient to add it in the mixer<br><small><em>Should appear in the mixer after tap</em></small></td>
       <td align="center">❌</td> </tr>
  <tr> <td> </td>
       <td>Add "liquid" ingredients to the mixer<br><small><em>Ingredients appear stacked over each other</em></small></td>
       <td align="center">✅</td> </tr>
  <tr> <td> </td>
       <td>Add "solid" ingredients to the mixer<br><small><em>Ingredients appear inside the liquid contents (if alreay present)</em></small></td>
       <td align="center">✅</td> </tr>
  <tr> <td> </td>
       <td>Show the count of added ingredients<br><small><em>The count is shown in the ingredients list after they were added in the mixer</em></small></td>
       <td align="center">❌</td> </tr>
  <tr> <td><b>Push</b></td>
       <td>Mix ingredients when tapping the button<br><small><em>Liquids are mixed together to create a cocktail in the mixer</em></small></td>
       <td align="center">✅</td> </tr>
  <tr> <td> </td>
       <td>Resulting liquid color<br><small><em>Mix the liquid colors according to their relative proportion</em></small></td>
       <td align="center">❌</td> </tr>
  <tr> <td><b>Serve<b></td>
       <td>Modal screen to show results<br><small><em>Opens when tapping the "Serve" button after a drink is completed</em></small></td>
       <td align="center">✅</td> </tr>
  <tr> <td> </td>
       <td>Fetch results form the server<br><small><em>Refer to the <a href="https://github.com/mirego/csgames19-competition/tree/master/api">API documentation</a> for the request and response</em></small></td>
       <td align="center">❌</td> </tr>
  <tr> <td> </td>
       <td>Show recipe results<br><small><em>Refer to the <a href="https://user-images.githubusercontent.com/4378424/54859824-2fd32080-4ce8-11e9-902a-6b992ac95823.png">design mockup</a> for visual inspiration</em></small></td>
       <td align="center">✅</td> </tr>
  <tr> <td> </td>
       <td>Success animation<br><small><em>When the recipe is rated at 100% 🎉</em></small></td>
       <td align="center">✅</td> </tr>
  <tr> <td> </td>
       <td>New drink<br><small><em>Mixer cleanup to restart the process after a drink service</em></small></td>
       <td align="center">✅</td> </tr>
</tr></tbody></table>

The challenge here is to fill the feature gaps to complete the app as it would be developed in its final fashion. Features may be developed the way you like, but the existing app structure should align you in the process.

Each feature to complete has a value of **10 pts**, for a total of **70 pts** for this track.

### 💡 Creative Features

If everything goes well or you want to try something else, you also have the opportunity to innovate in order to accumulate points.

- Invent a new feature for the application that is not listed below.
- Find a feature that will bring some added value to the app, and implement it in order to impress us.

This track may award you up to **10 pts**.

### 🍹 Perfect Recipe

When submitting recipes to the server, he will give you a rating based on various criteria.

- If you succeed to find the perfect combination of ingredients for a result of 100%, specify it in your submission notes.
- Please don't raid our server with requests – it is the same shared server for every participants of the competition, be a good citizen by keeping it organic.

This track may award you up to **10 pts**.

## Evaluation

We're not asking to do connect the data source, implement **all** display modes and add **all** the features listed above. In 3 hours, we ask you to do as much as possible and make full use of your creativity – the ultimate goal is to impress us.

The objective is not necessarily to complete **all** elements above in 3 hours. That being said, each item has the same point value, but they don't necessarily have the same complexity. We leave it up to you to prioritize and manage your time consequently, in order to maximize your success.

### Criteria

Your solutions will be marked using the following grid:

<table><thead><tr><th>Criteria</th><th>Points</th></tr></thead>
  <tbody><tr><td>
        <p><strong>🛠 Listed Features (×7)</strong></p>
        <ul>
          <li>Feature is completed and compliant</li>          <li>Development quality (code and cleanliness)</li>          <li>Integration quality (appearance and usability)</li>
          <li>Respect of design mockups (if applicable)</li>
        </ul>
      </td><td align="right" valign="top"><strong>/ 70</td></tr><tr><td>
        <p><strong>💡 Creative Features</strong></p>
        <ul>
          <li>Originality and innovation</li>          <li>Development quality (code and cleanliness)</li>          <li>Integration quality (appearance and usability)</li>
        </ul>
      </td>
      <td align="right" valign="top"><strong>/ 10</strong></td>
    </tr>
    <tr>
      <td>
        <p><strong>🍹 Perfect Recipe</strong></p>
        <ul>
          <li>Ingredients list with exact quantities</li>          <li>100% result is validated with the server</li>
        </ul>
      </td>
      <td align="right" valign="top"><strong>/ 10</strong></td>
    </tr>
    <tr>
      <td>
        <p><strong>📂 Submission</strong></p>
        <ul>
          <li>Pull Request correctly submitted</li>
          <li>Solution description provided and well-documented</li>
        </ul>
      </td>
      <td align="right" valign="top"><strong>/ 10</strong></td>
    </tr>
  </tbody>
  <tfoot>
    <tr>
      <td><strong>Total</strong></td>
      <td align="right"><strong>/ 100</strong></td>
    </tr>
  </tfoot>
</table>

Other important points:

- A project that **doesn't build**, after minimal efforts on our end, will be penalized by **-50 pts**.

- Your code must be based on our sample projects and be submitted using a proper Pull Request, otherwise you will be penalized by **-25 pts**.

- The submission timestamp must not be greater than the end of the competition. If we catch you cheating, you will get **0 / 100**.

### Submission

<table><thead><tr><th align="center">
⚠️ Please keep 10 minutes before the end of the competition to prepare the submission ⚠️ 
</th></tr></thead></table>

The submission will be done by adding a **Pull Request** to this GitHub repository.

> If you are not familiar with the concept, please refer to these articles:
> - [Fork a repo](https://help.github.com/articles/fork-a-repo/)
> - [Creating a pull request from a fork](https://help.github.com/articles/creating-a-pull-request-from-a-fork/).

Your **Pull Request** must respect the following pattern:

- **Title**: Your team code (exemple: `DaStreez `)
- **Description**: Use the Markdown template below by replacing the text in `{}`

```markdown
## Team

**Team Name:** {DaStreez}
**Team Number:** {1}
**University:** {Griffintown University}

**Authors:**
- {Hugo Lefrançois}
- {Mathieu Larue}

## Solution

**Platform:** {iOS | Android}

**Features:**

| Feature                                         | Completed |
| ----------------------------------------------- |:---------:|
| Fetch ingredients from the server               | ✔         |
| Fetch secret ingredients from the server        | {?}       |
| Show the ingredients list                       | {?}       |
| Tap on an ingredient to add it in the mixer     | {?}       |
| Show the count of added ingredients             | {?}       |
| Resulting liquid color                          | {?}       |
| Fetch results form the server                   | {?}       |
| {New feature}                                   | {?}       |

**Your best recipe:**

- {...}

**What went well or wrong:**

- {...}
- {...}

**What you are proud or disappointed of:**

- {...}
- {...}

```

> **NOTE:** If you don't have a GitHub account, raise your hand at the beginning of the challenge, we can provide you with a generic account.

## Acknowledgements

Thanks to the folks from Mirego behind this competition:

- [Émile Bélair](https://www.linkedin.com/in/emilebelair/) (Competition Director)
- [Hugo Lefrançois](https://www.linkedin.com/in/hugo-lefran%C3%A7ois-7419241/) (Lead Developer)
- [Olivier Pineau](https://www.linkedin.com/in/olivierpineau/) (iOS Developer)
- [Mathieu Larue](https://www.linkedin.com/in/mathieu-larue-871055a6/) (Android Developer)
- [Olivier Aubin](https://www.linkedin.com/in/olivier-aubin-40678632/) (UI Designer)
- [Claude Cormier](https://www.linkedin.com/in/claudecormier/) (Culture & Happiness)

## License

This competition is © 2019 [Mirego](http://www.mirego.com) and may be freely
distributed under the [New BSD license](http://opensource.org/licenses/BSD-3-Clause).
See the [`LICENSE.md`](https://github.com/mirego/csgames19-competition/blob/master/LICENSE.md) file.

## About Mirego

[Mirego](http://mirego.com) is a team of passionate people who believe that work is a place where you can innovate and have fun. We're a team of [talented people](http://life.mirego.com) who imagine and build beautiful Web and mobile applications. We come together to share ideas and [change the world](http://mirego.org).

We also [love open-source software](http://open.mirego.com) and we try to give back to the community as much as we can.
