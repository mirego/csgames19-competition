import UIKit

class IngredientsSectionViewModel {
    init(ingredientHolders: [IngredientHolder], title: String) {
        self.title = title
        ingredientViewModels = ingredientHolders.map { IngredientViewModel(ingredientHolder: $0) }
    }

    let ingredientViewModels: [IngredientViewModel]
    let title: String
}
