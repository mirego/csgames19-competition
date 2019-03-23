//
//  ServiceFactory.swift
//  MixParadise
//
//  Created by Alexandre Frigon on 2019-03-23.
//  Copyright © 2019 Mirego. All rights reserved.
//

class ServiceFactory {
    private let uniqueIngredientService = IngredientService()

    func ingredientService() -> IngredientService {
        return self.uniqueIngredientService
    }
}
