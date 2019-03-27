import Foundation

protocol RootViewModelListener: AnyObject {
    func didAddIngredient(ingredient: BlenderIngredientViewModel)
    func blenderDidReset()
}

class RootViewModel {
    private let blenderService: BlenderService
    private let ingredientsWebService: IngredientsWebService

    weak var listener: RootViewModelListener?

    init(blenderService: BlenderService, ingredientsWebService: IngredientsWebService) {
        self.blenderService = blenderService
        self.ingredientsWebService = ingredientsWebService
        blenderService.listener = self
    }
}

extension RootViewModel: BlenderServiceListener {
    func didAddIngredient(ingredient: Ingredient) {
        switch ingredient.type {
        case .solid:
            guard let solid = ingredient as? Solid, let spritePath = solid.randomSpriteUrl(),  let url = URL(string: spritePath) else {
                return
            }
            ingredientsWebService.getImage(from: url) { [weak self] (image) in
                self?.listener?.didAddIngredient(ingredient: BlenderIngredientViewModel(image: image))
            }
        case .liquid:
            listener?.didAddIngredient(ingredient: BlenderIngredientViewModel(liquid: ingredient as! Liquid))
        }
    }

    func blenderDidReset() {
        listener?.blenderDidReset()
    }
}
