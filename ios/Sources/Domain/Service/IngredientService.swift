//
//  IngredientService.swift
//  MixParadise
//
//  Created by Alexandre Frigon on 2019-03-23.
//  Copyright © 2019 Mirego. All rights reserved.
//

import Foundation

class IngredientService {
    func getList(callback: ([Ingredients]?, RequestError?) -> Void) {
        Request("\(Config.host)ingredients").auth().object { (res: ObjectResponse<Ingredients>) in
            return callback(res.object, res.error)
        }
    }

    //func serve(ingredients: [Ingredient], callback: (RequestError?) -> Void)
}
