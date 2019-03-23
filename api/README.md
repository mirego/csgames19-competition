# MixParadise Server

Base URL: **[https://mirego-csgames19.herokuapp.com/](https://mirego-csgames19.herokuapp.com/)**

Endpoints:

- **[`/ingredients`](#ingredients)**
- **[`/serve`](#serve)**

Authentication:

- ⚠️ All endpoints require a header `Team` to contain the name of your team (e.g. `Cruisaders`)

## Ingredients

### GET `/ingredients`

Returns the list of ingredients available to mix.

#### Response

| parameter         | type     | description                               |
| ----------        | -------- | ----------------------------------------- |
| `juices`          | _array_  | List of juices                            |
| `drinks`          | _array_  | List of soft drinks                       |
| `ingredients`     | _array_  | List of ingredients                       |
| (Objects ↓)       | _object_ |                                           |
| -- `id`           | _string_ | ID of the liquid                          |
| -- `label`        | _string_ | Text to display for the liquid            |
| -- `type`         | _string_ | Type of the object (`liquid` or `solid`)  |
| (Liquid type ↓)   | _object_ |                                           |
| -- `color`        | _string_ | Hex color of the liquid                   |
| -- `opacity`      | _double_ | Opacity of the liquid (0.0 - 1.0)         |
| -- `imageUrl`     | _string_ | Image to display for the liquid           |
| (Solid type ↓)    | _object_ |                                           |
| -- `weight`       | _double_ | Weight of the ingredient (0.0 - 1.0)      |
| -- `imageUrl`     | _string_ | Image to display for the ingredient       |
| -- `sprites`      | _array_  | Array of imageUrls for solid sprites      |

#### Sample response

```json
{
  "juices": [
    {
      "id": "orange",
      "label": "Orange",
      "color": "#f5a22c",
      "type": "liquid",
      "opacity": 0.4,
      "imageUrl": "https://s3.amazonaws.com/shared.ws.mirego.com/csgames19/assets/orange@3x.png"
    },
    ...
  ],
  "drinks": [...],
  "ingredients": [
    {
      "id": "mint",
      "label": "Mint",
      "type": "solid",
      "weight": 0.4,
      "imageUrl": "https://s3.amazonaws.com/shared.ws.mirego.com/csgames19/assets/menthe@3x.png",
      "sprites": [
        "https://s3.amazonaws.com/shared.ws.mirego.com/csgames19/assets/sprite-mint1@3x.png",
        "https://s3.amazonaws.com/shared.ws.mirego.com/csgames19/assets/sprite-mint2@3x.png",
        "https://s3.amazonaws.com/shared.ws.mirego.com/csgames19/assets/sprite-mint3@3x.png"
      ]
    }
  ],
  "alcohols": [...]
}
```

### GET `/ingredients` (authenticated)

The endpoint above can also be authenticated to receive more hidden ingredients.

* Add a **GET** parameter named `key` containing a random string
* Add an **HTTP Header** named `Authorization` containing the SHA1 checksum of a concatenation of the following strings, joined by a `-` dash:
   * The static `csgames19` strings
   * The current epoch time in minutes (epochSeconds / 60)
   * The same string as the `key` parameter

Sample values:

* `/ingredients?key=tv8PDnId7ylIwGEQ5naooq3wnL205RNR`
* `Authorization: b653fbb50d526643a87c7fca8f29ce0f2b281031` (at timestamp 23 March 2019 01:58:00 GMT)

## Serve

### POST `/serve`

Submits a recipe to the server to order it and evaluate it.

#### Request

| parameter  | type      | description                               |
| ---------- | --------- | ----------------------------------------- |
| (Array ↓)  | _array_   |                                           |
| `id`       | _string_  | ID of the ingredient                      |
| `qty`      | _integer_ | Quantity in ounces for the recipe         |

#### Sample request

```json
[
  {
    "id": "orange",
    "qty": 2
  },
  {
    "id": "mint",
    "qty": 1
  },
  ...
]
```

#### Response

| parameter     | type      | description                               |
| ------------- | --------- | ----------------------------------------- |
| `rating`      | _object_  | General rating of the recipe              |
| -- `note`     | _integer_ | -- Note for the drink [0, 100]            |
| -- `comment`  | _string_  | -- Text comment for the drink             |
| `review`      | _object_  | Criteria-based evaluation of the recipe   |
| -- `taste`    | _integer_ | -- Taste of the ingredients [0, 100]      |
| -- `volume`   | _integer_ | -- Volume of liquid in the glass [0, 100] |
| -- `strength` | _integer_ | -- Strength of the cocktail [0, 100]      |

#### Sample response

```json
{
  "rating": {
    "note": 83,
    "comment": "Yeah, I'd drink more than one."
  },
  "review": {
    "taste": 95,
    "volume": 86,
    "strength": 67
  }
}
```