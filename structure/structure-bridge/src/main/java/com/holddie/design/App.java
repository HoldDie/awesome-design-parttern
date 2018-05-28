package com.holddie.design;

import com.holddie.design.bridge.abstraction.FileDownloaderAbstraction;
import com.holddie.design.bridge.abstraction.FileDownloaderAbstractionImpl;
import com.holddie.design.bridge.implemention.LinuxFileDownloadImplementor;
import com.holddie.design.bridge.implemention.WindowsFileDownloadImplementor;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        System.out.println("Hello World!");

        String os = "linux";
        FileDownloaderAbstraction downloader = null;

        switch (os) {
            case "windows":
                downloader = new FileDownloaderAbstractionImpl(new WindowsFileDownloadImplementor());
                break;
            case "linux":
                downloader = new FileDownloaderAbstractionImpl(new LinuxFileDownloadImplementor());
                break;
            default:
                System.out.println("OS not supported!!");
        }

        Object fileContent = downloader.download("some path");
        downloader.store(fileContent);

    }

    /**
     * 桥接模式优化了原先使用继承时的子类爆炸问题，同时优化实现结构，转化为两层，使用组合模式Has-A而不是Is-A，
     * 使得在抽象功能和原先具体实现双方解耦，进行版本升级的时候会互不影响。
     *
     * 抽象和具体实现解耦（说三遍）
     */
}
