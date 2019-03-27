import Foundation

class ResultPageViewModel {
    private let blenderService: BlenderService
    private let ingredientsWebService: IngredientsWebService

    init(ingredientsWebService: IngredientsWebService, blenderService: BlenderService) {
        self.blenderService = blenderService
        self.ingredientsWebService = ingredientsWebService
    }

    func getResult(completion: @escaping (_ result: CallbackResult<ResultViewModel>) -> ()) {
        ingredientsWebService.validateIngredients(ingredients: buildServeIngredients(ingredients: blenderService.ingredients))  { result in
            switch result {
            case .Succes(let serveResponse):
                completion(CallbackResult.Succes(ResultViewModel(serveResponse: serveResponse)))
                break
            case .Error(let error):
                completion(CallbackResult.Error(error))
            }
        }
        blenderService.clearData()
    }

    private func buildServeIngredients(ingredients: [Ingredient]) -> [ServeIngredient] {
        return ingredients.map { return ServeIngredient(id: $0.id, qty: 1) }
    }
}

func delay(_ delay: Double, closure:@escaping () -> (Void)) {
    let time = DispatchTime.now() + Double(Int64(delay * Double(NSEC_PER_SEC))) / Double(NSEC_PER_SEC)
    DispatchQueue.main.asyncAfter(deadline: time, execute: closure)
}
