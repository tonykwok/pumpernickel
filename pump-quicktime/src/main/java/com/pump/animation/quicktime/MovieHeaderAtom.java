/**
 * This software is released as part of the Pumpernickel project.
 * 
 * All com.pump resources in the Pumpernickel project are distributed under the
 * MIT License:
 * https://raw.githubusercontent.com/mickleness/pumpernickel/master/License.txt
 * 
 * More information about the Pumpernickel project is available here:
 * https://mickleness.github.io/pumpernickel/
 */
package com.pump.animation.quicktime;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

import javax.media.jai.PerspectiveTransform;

import com.pump.io.GuardedOutputStream;

/** This is not a public class because I expect to make some significant
 * changes to this project in the next year.
 * <P>Use at your own risk.  This class (and its package) may change in future releases.
 * <P>Not that I'm promising there will be future releases.  There may not be.  :)
 */
public class MovieHeaderAtom extends LeafAtom {
	int version = 0;
	int flags = 0;
	Date creationTime;
	Date modificationTime;
	long timeScale;
	long duration;
	float preferredRate = 1;
	float preferredVolume = 1;
	PerspectiveTransform matrix;
	long previewTime = 0;
	long previewDuration = 0;
	long posterTime = 0;
	long selectionTime = 0;
	long selectionDuration = 0;
	long currentTime = 0;
	long nextTrackID = -1;
	
	public MovieHeaderAtom(Atom parent,InputStream in) throws IOException {
		super(parent);
		
		version = in.read();
		flags = read24Int(in);
		creationTime = readDate(in);
		modificationTime = readDate(in);
		timeScale = read32Int(in);
		duration = read32Int(in);
		preferredRate = read16_16Float(in);
		preferredVolume = read8_8Float(in);
		skip(in,10); //reserved
		matrix = readMatrix(in);
		previewTime = read32Int(in);
		previewDuration = read32Int(in);
		posterTime = read32Int(in);
		selectionTime = read32Int(in);
		selectionDuration = read32Int(in);
		currentTime = read32Int(in);
		nextTrackID = read32Int(in);	
	}
	
	public MovieHeaderAtom(long timeScale,long duration) {
		super(null);
		creationTime = new Date();
		modificationTime = creationTime;
		this.duration = duration;
		this.timeScale = timeScale;
		matrix = new PerspectiveTransform();
	}
	
	public void setNextTrackID(int id) {
		nextTrackID = id;
	}
	
	public long getNextTrackID() {
		return nextTrackID;
	}
	
	@Override
	protected String getIdentifier() {
		return "mvhd";
	}

	@Override
	protected long getSize() {
		return 108;
	}

	@Override
	protected void writeContents(GuardedOutputStream out) throws IOException {
		out.write(version);
		write24Int(out,flags);
		writeDate(out,creationTime);
		writeDate(out,modificationTime);
		write32Int(out,timeScale);
		write32Int(out,duration);
		write16_16Float(out,preferredRate);
		write8_8Float(out,preferredVolume);
		write32Int(out,0);
		write32Int(out,0);
		write16Int(out,0);
		writeMatrix(out,matrix);
		write32Int(out,previewTime);
		write32Int(out,previewDuration);
		write32Int(out,posterTime);
		write32Int(out,selectionTime);
		write32Int(out,selectionDuration);
		write32Int(out,currentTime);
		write32Int(out,getRoot().getHighestTrackID()+1);
	}

	@Override
	public String toString() {
		return "MovieHeaderAtom[ "+
		"version = "+version+", "+
		"flags = "+flags+", "+
		"creationTime = "+creationTime+", "+
		"modificationTime = "+modificationTime+", "+
		"timeScale = "+timeScale+", "+
		"duration = "+duration+", "+
		"preferredRate = "+preferredRate+", "+
		"preferredVolume = "+preferredVolume+", "+
		"matrix = "+matrix+", "+
		"previewTime = "+previewTime+", "+
		"previewDuration = "+previewDuration+", "+
		"posterTime = "+posterTime+", "+
		"selectionTime = "+selectionTime+", "+
		"selectionDuration = "+selectionDuration+", "+
		"currentTime = "+currentTime+", "+
		"nextTrackID = "+nextTrackID+"]";
	}

}