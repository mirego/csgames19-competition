//
//  Extensions.swift
//  MixParadise
//
//  Created by Alexandre Frigon on 2019-03-23.
//  Copyright © 2019 Mirego. All rights reserved.
//

extension Request {
    func auth() -> Request {
        self.addHeader(RequestHeader.init(name: "Team", value: "The Floor is LAVAL"))

//        let key = String.randomString(length: 32)
//        self.params(["key": key])
//
//        let token = "csgames19\(NSDate().timeIntervalSince1970 / 60)\(key)"
//        self.addHeader(RequestHeader.init(name: "Authorization", value: token))
        return self
    }
}

extension String {
    static func randomString(length: Int) -> String {
        let letters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789"
        return String((0..<length).map{ _ in letters.randomElement()! })
    }
}

