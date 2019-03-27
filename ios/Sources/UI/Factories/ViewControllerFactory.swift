class ViewControllerFactory {
    private let viewModelFactory: ViewModelFactory

    init(viewModelFactory: ViewModelFactory) {
        self.viewModelFactory = viewModelFactory
    }

    func rootViewController() -> RootViewController {
        return assign(RootViewController(rootViewModel: viewModelFactory.rootViewModel()))
    }

    func ingredientsPickerViewController() -> IngredientsPickerViewController {
        return assign(IngredientsPickerViewController(viewModel: viewModelFactory.ingredientsPickerViewModel()))
    }

    func resultViewController() -> ResultViewController {
        return assign(ResultViewController(viewModel: viewModelFactory.resultPageViewModel()))
    }
}

private extension ViewControllerFactory {
    func assign<T: BaseViewController>(_ viewController: T) -> T {
        viewController.viewControllerFactory = self
        return viewController
    }
}
