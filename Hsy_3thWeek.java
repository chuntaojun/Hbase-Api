// HDFS的API使用说明：
// 　　1.如果要访问HDFS，HDFS客户端必须有一份HDFS的配置文件
// 　　也就是hdfs-site.xml,从而读取Namenode的信息。
// 　　2.每个应用程序也必须拥有访问Hadoop程序的jar文件
// 　　3.操作HDFS,也就是HDFS的读和写,最常用的类FileSystem

//*******************>>HDFS的读操作：
/**

使用java.net.URL访问HDFS文件系统，一共有两种方法可
采用。
方法一：使用java.net.URL对象打开数据流，
从中读取数据，并且还要通过FsUrlStreamandlerFactory
实例调用java.net.URL对象的setFsUrlStreamhandFactory()方法。
具体格式API如下：


**/

static 
{
	URL.setFsUrlStreamHandlerFactory(new FsUrlstreamHandlerFactory());
}

InputStream in = null;
 try
 {
	in = new URL("hdfs://host/path").openStream();
	
 }
 
 finally
 {
	 IOUtils.closestream(in);
 } 


/**
方法二：因为每个Java虚拟机只能调用一次setFsUrlStreamHandlerFactory方法，即
即意味着如果程序的其他组件已经声明一个UrlStreamHandlerFactory时，程序员将无法使用
这种方法从Hadoop中读取数据，所以此时需要用FileSystem API来打开一个文件的输入流
以下是获取FileSystem实例的几个静态工厂方法：
*/

public static FileSystem get(Configuration conf) throws IOException
public static FileSystem get(URI uri, Configuration conf) throws IOException
public static FileSystem get(URI uri, Configuration conf, String user) throws IOException
/**
解释：Configuration对象封装了客户端或服务器的配置， 通过设置配置文件读取类路径
来实现(如etc/hadoop/core-site.xml)。第一个方法返回的是默认文件系统(在core-site.xml指定的，如果没有指定，则使用默认的本地文件系统);
第二个方法通过给定的URI方案和权限来确定要使用的文件系统，如果给定的URI中没有指定方案，
则返回默认文件系统。第三个方法是作为给定用户来访问系统，对安全来说是至关重要的。

*/

public static LocalFileSystem getLocal(Configuration conf) throws IOException//使用这个方法
// 可以很方便地获取本地文件系统的运行实例


public FSDataInputStream open(Path f) throws IOException
public abstract FsDataInputStream open(Path f, int bufferSize) throws IOException//有了FileSystem实例后，
//其中第一个方法使用默认的缓冲区大小4KB

/**
FsDataInputStream类是继承了java.io.DataInputStream的
一个特殊类，并支持随机访问，由此可以从流的任意位置读取数据，
以下介绍FsDataInputStream类所实现的Seekable接口、PositionedReadable接口、
*/

public interface Seekable
{
	void seek(long pos) throws IOException;
	long getPos() throws IOException;
}//Seekable接口支持在文件中找到指定位置，并提供一个查询
// 当前位置相对于文件起始位置偏移量(getPos())的查询方法

public interface PositionedReadable 
{
	public int read(long position, byte[] buffer, int offset, int length) throws IOException;
	
	public void readFully(long position, byte[] buffer, in t offset, int length) throws IOException;
	
	public void readFully(long position, byte[] buffer) throws IOException;
		
}

//*******************>>HDFS的写操作：

/**
FileSystem类有一系列新建文件的方法。最简单的方法是给准备
建的文件指定一个Path对象，然后返回一个用于写入数据的输出流
*/

public FSDataOutputStream create(Path f) throws IOException//此方法
// 有多个重载版本，允许我们指定是否需要强制覆盖现有的文件、文件备份
// 数量、写入文件时所用缓冲区大小、文件块大小以及权限

package org.apache.hadoop.util;

public interface Progressable
{
	public void progress();
}//这个Progressable重载方法可用于传递回调接口，如此一来，
// 可以把数据写入datanode的进度通知给应用

public FSDataOutputStream append(Path f) throws IOException
//这个方法提供一种追加操作，可允许一个writer打开文件后在访问该文件的
// 最后偏移量处追加数据。






























































	



