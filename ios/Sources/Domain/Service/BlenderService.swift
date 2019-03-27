import Foundation

protocol BlenderServiceListener: AnyObject {
    func didAddIngredient(ingredient: Ingredient)
    func blenderDidReset()
}

class BlenderService {
    var ingredients: [Ingredient] = []

    weak var listener: BlenderServiceListener?

    func addIngredient(_ ingredient: Ingredient) {
        ingredients.append(ingredient)
        listener?.didAddIngredient(ingredient: ingredient)
    }

    func clearData() {
        ingredients.removeAll()
        listener?.blenderDidReset()
    }

    func getCount(ingredient: Ingredient) -> Int {
        return ingredients.reduce(0, { (result, localIngredient) -> Int in
            return result + ((ingredient.id == localIngredient.id) ? 1 : 0)
        })
    }
}
