package com.cmtv.tv.util.img;

import java.io.InputStream;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class ImgUtil {

	public static Bitmap decodeSampledBitmapFromResource(Resources resources, int id, int size) {
		InputStream inputStream = resources.openRawResource(id);
		BitmapFactory.Options options = new BitmapFactory.Options();
		options.inPurgeable = true;
		options.inSampleSize = size;
		options.inInputShareable = true;
		return BitmapFactory.decodeStream(inputStream, null, options);
	}
}
