import Foundation

class ViewModelFactory {
    private let ingredientsWebService: IngredientsWebService
    private let blenderService: BlenderService

    init(ingredientsWebService: IngredientsWebService, blenderService: BlenderService) {
        self.ingredientsWebService = ingredientsWebService
        self.blenderService = blenderService
    }

    func ingredientsPickerViewModel() -> IngredientsPickerViewModel {
        return IngredientsPickerViewModel(ingredientsWebService: ingredientsWebService, blenderService: blenderService)
    }

    func rootViewModel() -> RootViewModel {
        return RootViewModel(blenderService: blenderService, ingredientsWebService: ingredientsWebService)
    }

    func resultPageViewModel() -> ResultPageViewModel {
        return ResultPageViewModel(ingredientsWebService: ingredientsWebService, blenderService: blenderService)
    }
}
