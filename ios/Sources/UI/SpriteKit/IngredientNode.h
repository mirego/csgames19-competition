//
//  Rock.h
//  DynamicWater
//
//  Created by Steve Barnegren on 08/03/2016.
//  Copyright © 2016 Steve Barnegren. All rights reserved.
//

#import <SpriteKit/SpriteKit.h>

@interface IngredientNode : SKSpriteNode

@property BOOL isAboveWater;
@property CGPoint velocity;
@property CGPoint initialPosition;

@end
