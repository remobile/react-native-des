<?php

function encrypt($str, $key)
{
    $block = mcrypt_get_block_size('des', 'ecb');
    $pad = $block - (strlen($str) % $block);
    $str .= str_repeat(chr($pad), $pad);
    return base64_encode(mcrypt_encrypt(MCRYPT_DES, $key, $str, MCRYPT_MODE_ECB));
}

function decrypt($str, $key)
{
    $str = mcrypt_decrypt(MCRYPT_DES, $key, base64_decode($str), MCRYPT_MODE_ECB);
    $block = mcrypt_get_block_size('des', 'ecb');
    $pad = ord($str[($len = strlen($str)) - 1]);
    return substr($str, 0, strlen($str) - $pad);
}

$a = encrypt('fangyunjiang is a good developer', 'ABCDEFGH');
$b = decrypt($a, 'ABCDEFGH');
echo $a;
echo "\n";
echo $b;

?>
