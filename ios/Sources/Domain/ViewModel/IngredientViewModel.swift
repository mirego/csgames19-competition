class IngredientViewModel {
    let ingredientHolder: IngredientHolder

    init(ingredientHolder: IngredientHolder) {
        self.ingredientHolder = ingredientHolder
    }

    var count: Int {
        return ingredientHolder.count
    }

    var imageUrl: String {
        get {
            return ingredientHolder.ingredient.imageUrl
        }
    }

    var name: String {
        get {
            return ingredientHolder.ingredient.label
        }
    }
}
