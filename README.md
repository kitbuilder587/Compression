# Introduction into base classes

<a href="https://github.com/kitbuilder587/Compression/blob/master/src/com/kitSoft/FileLzwArchiver.java">FileLzwArchiver</a> contains methods to code and decode file using <a href="https://github.com/kitbuilder587/Compression/blob/master/src/com/kitSoft/LzwImplementation.java">LzwImplimentation</a>.  

<a href="https://github.com/kitbuilder587/Compression/blob/master/src/com/kitSoft/BitArray.java">BitArray</a> is a class that provides easy convertion a binary array of any size to byte array.

<a href="https://github.com/kitbuilder587/Compression/blob/master/src/com/kitSoft/HaffmanImplementation.java">HaffmanImplementation</a> is a class that contains algorithm to compress strings.

<a href="https://github.com/kitbuilder587/Compression/blob/master/src/com/kitSoft/HaffmanCoder.java">HaffmanCoder</a> and <a href="https://github.com/kitbuilder587/Compression/blob/master/src/com/kitSoft/HaffmanDecoder.java">HaffmanDecoder</a> are classes that packs tree and compressed string of haffman algorithm to files.


<h3> Array Binaries update </h3>

Added interface <a href="https://github.com/kitbuilder587/Compression/blob/master/src/com/kitSoft/ArrayBinaries.java">ArrayBinaries</a> that is implemented by classes that could pack an integer array. This one of the steps of Lzw algorithm. One of this classes is <a href="https://github.com/kitbuilder587/Compression/blob/master/src/com/kitSoft/DynamicSizeArrayBinaries.java">DynamicSizeArrayBinaries</a> that can operate with coding integers to binary codes with different sizes. It based on idea that element i of array couldn't be greater than i+2. By that optimization simple text on wikipedia could be compressed to the half of its size.

