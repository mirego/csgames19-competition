//
//  Model.swift
//  MixParadise
//
//  Created by Morteza Ahmadi on 2019-03-23.
//  Copyright © 2019 Mirego. All rights reserved.
//

import Foundation

struct Model: Codable {
    let juices: [Juice]
    let drinks: [Drink]
    let ingredients: [Ingredient]
}

struct Juice: Codable {
    let id: String?
    let label: String?
    let type: String?
    let color: String?
    let opacity: Double?
    let imageUrl: String?
}

struct Drink: Codable {
    let id: String?
    let label: String?
    let type: String?
    let color: String?
    let opacity: Double?
    let imageUrl: String?
}

struct Ingredient: Codable {
    let id: String?
    let label: String?
    let type: String?
    let weight: Double?
    let imageUrl: String?
    let sprites: [String]?
}

