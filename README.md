Here are some java utils, each one is a single java source without any dependencies. You can use them free for any purpose. 

URLParser
=========

A simple url parser, we can use it to parse a uri and get the params and other information.

    String url = "ftp://www.test.com/aaa/bbb;xxx=xxx?eee=111&fff=222&fff=333";

    URLParser parser = new URLParser(url);

    // get basic infomation
    System.out.println(parser.getHost());
    System.out.println(parser.getPort());
    System.out.println(parser.getProtocol());
    System.out.println(parser.getPath());
    System.out.println(parser.getQuery());
    System.out.println(parser.getUserInfo());

    // get paramsa
    System.out.println(parser.getParam("eee"));
    System.out.println(parser.getParam("fff"));
    System.out.println(parser.getParams("fff"));

    // update params
    parser.removeParams("eee");
    parser.addParam("ggg", "444");
    parser.updateParams("fff", "555");

    // create query string
    System.out.println(parser.createQueryString());

    // full url
    System.out.println(parser.toString());

    // with charset
    String url2 = "http://localhost:8080/search?name=中文";
    URLParser parser2 = new URLParser(url2);
    System.out.println(parser2.getParam("name"));
    System.out.println(parser2.toString());

    parser2.encode("UTF8");
    System.out.println(parser2.getParam("name"));
    System.out.println(parser2.toString());

    parser2.decode("UTF8");

WaterMarker
===========
Draw a text or a image as a watermarker to another image.

File sourceImage = new File("c:/source.jpg");

    // Use text
    new WaterMarker(sourceImage, "MyTest").setPosition(Position.leftTop).generateTo(new File("c:/text.leftTop.jpg"));
    new WaterMarker(sourceImage, "MyTest").setPosition(Position.top).generateTo(new File("c:/text.top.jpg"));
    new WaterMarker(sourceImage, "MyTest").setPosition(Position.rightTop).generateTo(new File("c:/text.rightTop.jpg"));
    new WaterMarker(sourceImage, "MyTest").setPosition(Position.left).generateTo(new File("c:/text.left.jpg"));
    new WaterMarker(sourceImage, "MyTest").setPosition(Position.center).generateTo(new File("c:/text.center.jpg"));
    new WaterMarker(sourceImage, "MyTest").setPosition(Position.right).generateTo(new File("c:/text.right.jpg"));
    new WaterMarker(sourceImage, "MyTest").setPosition(Position.leftBottom).generateTo(new File("c:/text.leftBottom.jpg"));
    new WaterMarker(sourceImage, "MyTest").setPosition(Position.bottom).generateTo(new File("c:/text.bottom.jpg"));
    new WaterMarker(sourceImage, "MyTest").setPosition(Position.rightBottom).generateTo(new File("c:/text.rightBottom.jpg"));
    
    // Use image
    File waterImage = new File("c:/water.jpg");
    new WaterMarker(sourceImage, waterImage).setPosition(Position.leftTop).generateTo(new File("c:/water.leftTop.jpg"));
    new WaterMarker(sourceImage, waterImage).setPosition(Position.top).generateTo(new File("c:/water.top.jpg"));
    new WaterMarker(sourceImage, waterImage).setPosition(Position.rightTop).generateTo(new File("c:/water.rightTop.jpg"));
    new WaterMarker(sourceImage, waterImage).setPosition(Position.left).generateTo(new File("c:/water.left.jpg"));
    new WaterMarker(sourceImage, waterImage).setPosition(Position.center).generateTo(new File("c:/water.center.jpg"));
    new WaterMarker(sourceImage, waterImage).setPosition(Position.right).generateTo(new File("c:/water.right.jpg"));
    new WaterMarker(sourceImage, waterImage).setPosition(Position.leftBottom).generateTo(new File("c:/water.leftBottom.jpg"));
    new WaterMarker(sourceImage, waterImage).setPosition(Position.bottom).generateTo(new File("c:/water.bottom.jpg"));
    new WaterMarker(sourceImage, waterImage).setPosition(Position.rightBottom).generateTo(new File("c:/water.rightBottom.jpg"));