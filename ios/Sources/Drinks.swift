//
//  Drinks.swift
//  MixParadise
//
//  Created by Elisa Kazan on 2019-03-23.
//  Copyright © 2019 Mirego. All rights reserved.
//

import Foundation

struct GenCollection: Codable{
            let alcohols: [Alcohol]
            let juices: [Juice]
            let ingredients: [Ingredient]
            let drinks: [Drink]
}
struct Alcohol: Codable{
            //let id: String
}
struct Juice: Codable{
            let id: String
            let label: String
            let color: String
            let type: String
            let opacity: Float
            let imageUrl: String
}
struct Ingredient: Codable{
            let id: String
            let label: String
            let type: String
            let weight: Float
            let imageUrl: String
}
struct Drink: Codable{
    //    let id: String
    //    let label: String
    //    let color: String
    //    let type: String
    //    let opacity: Float
    //    let imageUrl: String
}
