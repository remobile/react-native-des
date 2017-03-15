//
//  DesBase64.m
//  Edu_tech
//
//  Created by fangyunjiang on 15/8/11.
//  Copyright (c) 2015年 gyyx. All rights reserved.
//

#import <CommonCrypto/CommonCryptor.h>
#import "GTMBase64.h"
#import "DesBase64.h"

@implementation DesBase64

+ (NSString *)encryptUseDES:(NSString *)clearText key:(NSString *)key vec:(NSString*)vec
{
    NSData *data = [clearText dataUsingEncoding:NSUTF8StringEncoding allowLossyConversion:YES];
    NSUInteger dataLength = [data length];
    size_t bufferSize = dataLength + kCCBlockSizeAES128;
    void *buffer = calloc(bufferSize, sizeof(char));
    size_t numBytesEncrypted = 0;
    CCOptions ccOp = kCCOptionPKCS7Padding | kCCOptionECBMode;
    void *iv = nil;
    if (vec) {
        iv = (void *)[vec UTF8String];
        ccOp = kCCOptionPKCS7Padding;
    }
    
    CCCryptorStatus cryptStatus = CCCrypt(kCCEncrypt,
                                          kCCAlgorithmDES,
                                          ccOp,
                                          [key UTF8String],
                                          kCCKeySizeDES,
                                          iv,
                                          [data bytes],
                                          dataLength,
                                          buffer,
                                          bufferSize,
                                          &numBytesEncrypted);
    
    NSString* plainText = nil;
    if (cryptStatus == kCCSuccess) {
        NSData *dataTemp = [NSData dataWithBytes:buffer length:(NSUInteger)numBytesEncrypted];
        plainText = [GTMBase64 stringByEncodingData:dataTemp];
    }else{
        NSLog(@"DES加密失败");
    }
    free(buffer);
    return plainText;
}

+ (NSString*)decryptUseDES:(NSString*)cipherText key:(NSString*)key vec:(NSString*)vec {
    // 利用 GTMBase64 解碼 Base64 字串
    NSData* cipherData = [GTMBase64 decodeString:cipherText];
    NSUInteger dataLength = [cipherData length];
    size_t bufferSize = dataLength + kCCBlockSizeAES128;
    void *buffer = calloc(bufferSize, sizeof(char));
    size_t numBytesDecrypted = 0;
    CCOptions ccOp = kCCOptionPKCS7Padding | kCCOptionECBMode;
    void *iv = nil;
    if (vec) {
        iv = (void *)[vec UTF8String];
        ccOp = kCCOptionPKCS7Padding;
    }

    CCCryptorStatus cryptStatus = CCCrypt(kCCDecrypt,
                                          kCCAlgorithmDES,
                                          ccOp,
                                          [key UTF8String],
                                          kCCKeySizeDES,
                                          iv,
                                          [cipherData bytes],
                                          dataLength,
                                          buffer,
                                          bufferSize,
                                          &numBytesDecrypted);
    NSString* plainText = nil;
    if (cryptStatus == kCCSuccess) {
        NSData* data = [NSData dataWithBytes:buffer length:(NSUInteger)numBytesDecrypted];
        plainText = [[NSString alloc] initWithData:data encoding:NSUTF8StringEncoding];
    }
    free(buffer);
    return plainText;
}
@end
