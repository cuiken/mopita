package com.tp.service;

import java.util.Observable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tp.entity.FileInfo;

@Component
public class FileInfoObservable extends Observable {

	private FileManager fileManager;
	private FileStoreInfoObserver observer;

	public void saveFileInfo(FileInfo info) {

		fileManager.saveFileInfo(info);
		addObserver(observer);
		setChanged();
		notifyObservers(info);
	}

	public void deleteFileInfo(Long id) {
		fileManager.deleteFileInfo(id);
		addObserver(observer);
		setChanged();
		notifyObservers(id);
	}

	@Autowired
	public void setFileManager(FileManager fileManager) {
		this.fileManager = fileManager;
	}

	@Autowired
	public void setObserver(FileStoreInfoObserver observer) {
		this.observer = observer;
	}
}
