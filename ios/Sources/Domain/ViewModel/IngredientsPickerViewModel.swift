import Foundation

class IngredientsPickerViewModel {
    private let ingredientsWebService: IngredientsWebService
    private let blenderService: BlenderService

    init(ingredientsWebService: IngredientsWebService, blenderService: BlenderService) {
        self.ingredientsWebService = ingredientsWebService
        self.blenderService = blenderService
    }

    func getSections(completion: @escaping (_ result: CallbackResult<[IngredientsSectionViewModel]>) -> ()) {
        ingredientsWebService.getIngredients { [weak self] result in
            guard let strongSelf = self else { return }
            switch result {
            case .Succes(let ingredients):
                completion(CallbackResult.Succes(strongSelf.buildSections(ingedients: ingredients)))
                break
            case .Error(let error):
                completion(CallbackResult.Error(error))
            }
        }
    }

    func didSelectIngredient(ingredientViewModel: IngredientViewModel) {
        blenderService.addIngredient(ingredientViewModel.ingredientHolder.ingredient)
    }

    private func buildSections(ingedients: IngredientsResponse) -> [IngredientsSectionViewModel] {
        var sections: [IngredientsSectionViewModel] = []
        if let juices = ingedients.juices, !juices.isEmpty {
            sections.append(IngredientsSectionViewModel(ingredientHolders: buildIngredientHolders(ingredients: juices), title: "Juices"))
        }
        if let drinks = ingedients.drinks, !drinks.isEmpty {
            sections.append(IngredientsSectionViewModel(ingredientHolders: buildIngredientHolders(ingredients: drinks), title: "Soft Drinks"))
        }
        if let ingredients = ingedients.ingredients, !ingredients.isEmpty {
            sections.append(IngredientsSectionViewModel(ingredientHolders: buildIngredientHolders(ingredients: ingredients), title: "Ingredients"))
        }
        if let alcohols = ingedients.alcohols, !alcohols.isEmpty {
            sections.append(IngredientsSectionViewModel(ingredientHolders: buildIngredientHolders(ingredients: alcohols), title: "Alcohols"))
        }
        return sections
    }

    private func buildIngredientHolders(ingredients: [Ingredient]) -> [IngredientHolder] {
        return ingredients.map { return IngredientHolder(ingredient: $0, count: blenderService.getCount(ingredient: $0)) }
    }
}
