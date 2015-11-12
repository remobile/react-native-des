//
//  RCTDes.m
//  RCTDes
//
//  Created by fangyunjiang on 15/11/4.
//  Copyright (c) 2015年 remobile. All rights reserved.
//


#import "RCTDes.h"
#import "DesBase64.h"

@implementation RCTDes
RCT_EXPORT_MODULE()

RCT_EXPORT_METHOD(encrypt:(NSString *)data key:(NSString *)key callback:(RCTResponseSenderBlock)callback) {
    NSString *base64 = [DesBase64 encryptUseDES:data key:key];
    callback(@[base64]);
}

RCT_EXPORT_METHOD(decrypt:(NSString *)base64 key:(NSString *)key callback:(RCTResponseSenderBlock)callback) {
    NSString *data = [DesBase64 decryptUseDES:base64 key:key];
    callback(@[data]);
    
}
@end
