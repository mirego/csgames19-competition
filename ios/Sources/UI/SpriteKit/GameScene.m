//
//  GameScene.m
//  DynamicWater
//
//  Created by Steve Barnegren on 07/03/2016.
//  Copyright (c) 2016 Steve Barnegren. All rights reserved.
//

#import "GameScene.h"
#import "SBDynamicWaterNode.h"
#import "IngredientNode.h"

#define kFixedTimeStep (1.0f/500)

#define kSurfaceHeight 235

typedef enum : NSUInteger {
    ZPositionSky,
    ZPositionRock,
    ZPositionWater,
} ZPositions;

@interface GameScene ()

@property (nonatomic, strong) SBDynamicWaterNode *waterNode;

@property CFTimeInterval lastFrameTime;
@property BOOL hasReferenceFrameTime;

@property (nonatomic, strong) NSMutableArray *rocks;

@end

@implementation GameScene

-(void)didMoveToView:(SKView *)view {
    
    self.rocks = [[NSMutableArray alloc]init];

//    SKSpriteNode* blender = [SKSpriteNode spriteNodeWithTexture: [SKTexture textureWithImageNamed:@"blender"]];
//    blender.position = CGPointMake(self.size.width / 2, blender.size.height / 4 * 3);
//    blender.zPosition = ZPositionSky;
//    [self addChild: blender];
//
//    SKSpriteNode* skySprite = [SKSpriteNode spriteNodeWithTexture: [SKTexture textureWithImageNamed:@"palmTrees"]];
//    skySprite.position = CGPointMake(self.size.width / 2, skySprite.size.height/2);
//    skySprite.zPosition = ZPositionSky;
//    [self addChild: skySprite];
//
//    SKSpriteNode* leftCloud = [SKSpriteNode spriteNodeWithTexture: [SKTexture textureWithImageNamed:@"leftCloud"]];
//    leftCloud.position = CGPointMake(self.size.width / 2 - leftCloud.size.width / 2, self.size.height - leftCloud.size.height / 2);
//    leftCloud.zPosition = ZPositionSky;
//    [self addChild: leftCloud];
//
//    SKSpriteNode* sun = [SKSpriteNode spriteNodeWithTexture: [SKTexture textureWithImageNamed:@"sun"]];
//    sun.position = CGPointMake(self.size.width / 2 + sun.size.width / 3, self.size.height - sun.size.height / 2);
//    sun.zPosition = ZPositionSky;
//    [self addChild: sun];
//
//    SKSpriteNode* rightCloud = [SKSpriteNode spriteNodeWithTexture: [SKTexture textureWithImageNamed:@"rightCloud"]];
//    rightCloud.position = CGPointMake(self.size.width / 2 + rightCloud.size.width, self.size.height - rightCloud.size.height / 2);
//    rightCloud.zPosition = ZPositionSky;
//    [self addChild: rightCloud];


    // Water
    self.waterNode = [[SBDynamicWaterNode alloc]initWithWidth:self.size.width
                                                    numJoints:100
                                                surfaceHeight:kSurfaceHeight
                                                   fillColour:[UIColor colorWithRed:0 green:0 blue:1 alpha:0.5]];
    self.waterNode.position = CGPointMake(self.size.width / 2, 0);
    self.waterNode.zPosition = ZPositionWater;
    [self addChild:self.waterNode];

//    UIView* bottomView = [[UIView alloc] initWithFrame:CGRectMake(0, self.size.height, self.size.width, skySprite.size.height / 3)];
//    [bottomView setBackgroundColor: [UIColor colorWithRed:0.168 green:0.196 blue:0.317 alpha:1]];
//    [self.view addSubview:bottomView];


    // Set Default Values
    [self setDefaultValues];

}

-(void)setDefaultValues{
    self.waterNode.surfaceHeight = kSurfaceHeight;
    self.splashWidth = 20;
    self.splashForceMultiplier = 0.125;
    [self.waterNode setDefaultValues];
}

#pragma mark - Touch Handling

-(void)touchesBegan:(NSSet *)touches withEvent:(UIEvent *)event {
    
    for (UITouch *touch in touches) {
        CGPoint location = [touch locationInNode:self];
        IngredientNode *rock = [[IngredientNode alloc]initWithImageNamed:@"rock"];
        rock.position = location;
        rock.zPosition = ZPositionRock;
        [self addChild:rock];
        [self.rocks addObject:rock];
    }
}

#pragma mark - Update

-(void)update:(CFTimeInterval)currentTime {
    /* Called before each frame is rendered */
    
    if (!self.hasReferenceFrameTime) {
        self.lastFrameTime = currentTime;
        self.hasReferenceFrameTime = YES;
        return;
    }
    
    CFTimeInterval dt = currentTime - self.lastFrameTime;
    
    // Fixed Update
    CFTimeInterval accumilator = 0;
    accumilator += dt;
    
    while (accumilator >= kFixedTimeStep) {
        [self fixedUpdate:kFixedTimeStep];
        accumilator -= kFixedTimeStep;
    }
    [self fixedUpdate:accumilator];
    
    // Late Update
    [self lateUpdate:dt];
    
    self.lastFrameTime = currentTime;
    
}

-(void)fixedUpdate:(CFTimeInterval)dt{
    [self.waterNode update:dt];

    
    NSMutableArray *rocksToRemove = [[NSMutableArray alloc]init];
    
    const float gravity = -1200;
    for (IngredientNode *rock in self.rocks) {
        
        // Apply Gravity
        rock.velocity = CGPointMake(rock.velocity.x,
                                    rock.velocity.y + gravity * dt);
    
        rock.position = CGPointMake(rock.position.x + rock.velocity.x * dt,
                                    rock.position.y + rock.velocity.y * dt);
        
        // Splash
        if (rock.isAboveWater && rock.position.y <= self.waterNode.surfaceHeight) {
            rock.isAboveWater = NO;
            [self.waterNode splashAtX:rock.position.x
                                force:-rock.velocity.y * self.splashForceMultiplier
                                width:self.splashWidth];

        }
        
        // Remove if off-screen
        if (rock.position.y < - rock.size.height/2) {
            [rocksToRemove addObject:rock];
        }
    }
    
    for (IngredientNode *rock in rocksToRemove) {
        [self.rocks removeObject:rock];
    }
    
}

-(void)lateUpdate:(CFTimeInterval)dt{
    [self.waterNode render];
}

@end
