//
//  TitleLabel.swift
//  MixParadise
//
//  Created by Alexandre Frigon on 2019-03-23.
//  Copyright © 2019 Mirego. All rights reserved.
//

import UIKit

class TitleLabel: UILabel {
    init(title: String) {
        super.init(frame: .zero)
        self.text = title
        self.font = Headline.font(.H2)
        self.textColor = .black
    }

    override func layoutSubviews() {
        self.pin.left()
    }
}
