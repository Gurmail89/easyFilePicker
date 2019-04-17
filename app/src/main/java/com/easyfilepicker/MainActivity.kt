package com.easyfilepicker

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import com.easyfilepicker.activity.AudioPickActivity
import com.easyfilepicker.activity.AudioPickActivity.IS_NEED_RECORDER
import com.easyfilepicker.activity.ImagePickActivity
import com.easyfilepicker.activity.ImagePickActivity.*
import com.easyfilepicker.activity.NormalFilePickActivity
import com.easyfilepicker.activity.VideoPickActivity
import com.easyfilepicker.filter.entity.AudioFile
import com.easyfilepicker.filter.entity.ImageFile
import com.easyfilepicker.filter.entity.NormalFile
import com.easyfilepicker.filter.entity.VideoFile
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {
    private val TAG = MainActivity::class.java!!.getSimpleName()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        image.setOnClickListener {
            var intent: Intent = Intent(this@MainActivity, ImagePickActivity::class.java)
            startActivityForResult(intent, Constant.REQUEST_CODE_PICK_IMAGE);
        }

        doc.setOnClickListener{
            val intent4 = Intent(this@MainActivity, NormalFilePickActivity::class.java)
            intent4.putExtra(Constant.MAX_NUMBER, 1)
            intent4.putExtra(
                Constant.SUFFIX,
                arrayOf(
                    "txt",
                    "xlsx",
                    "xls",
                    "doc",
                    "docX",
                    "ppt",
                    "pptx",
                    "pdf",
                    "ODT",
                    "apk",
                    "zip",
                    "CSV",
                    "SQL",
                    "PSD"
                )
            )

            startActivityForResult(intent4, Constant.REQUEST_CODE_PICK_FILE)
        }

        audio.setOnClickListener{
            val intent3 = Intent(this@MainActivity, AudioPickActivity::class.java)
            startActivityForResult(intent3, Constant.REQUEST_CODE_PICK_AUDIO)
        }

        video.setOnClickListener{
            val intent2 = Intent(this@MainActivity, VideoPickActivity::class.java)
            startActivityForResult(intent2, Constant.REQUEST_CODE_PICK_VIDEO)
        }
    }


//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//
//    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        when (requestCode) {
            Constant.REQUEST_CODE_PICK_IMAGE -> if (resultCode == RESULT_OK) {
                val list: ArrayList<ImageFile> = data!!.getParcelableArrayListExtra(Constant.RESULT_PICK_IMAGE)
                val builder = StringBuilder()
                for (image in list) {
                    builder.append(image.name+"\n")
                    builder.append(image.path+"\n")
                    builder.append(image.mimeType+"\n")
                    builder.append(image.bucketName+"\n")
                }
                Log.v(TAG, builder.toString());
            }
            Constant.REQUEST_CODE_PICK_VIDEO -> if (resultCode == RESULT_OK) {
                val list: ArrayList<VideoFile> = data!!.getParcelableArrayListExtra(Constant.RESULT_PICK_VIDEO)
                val builder = StringBuilder()
                for (video in list) {
                    builder.append(video.name+"\n")
                    builder.append(video.path+"\n")
                }
                Log.v(TAG, builder.toString());
            }
            Constant.REQUEST_CODE_PICK_AUDIO -> if (resultCode == RESULT_OK) {
                val list: ArrayList<AudioFile> = data!!.getParcelableArrayListExtra(Constant.RESULT_PICK_AUDIO)
                val builder = StringBuilder()
                for (audio in list) {
                    builder.append(audio.name+"\n")
                    builder.append(audio.path+"\n")
                }
                Log.v(TAG, builder.toString());
            }
            Constant.REQUEST_CODE_PICK_FILE -> if (resultCode == RESULT_OK) {
                val list: ArrayList<NormalFile> = data!!.getParcelableArrayListExtra(Constant.RESULT_PICK_FILE)
                val builder = StringBuilder()
                for (normal in list) {
                    builder.append(normal.name+"\n")
                    builder.append(normal.path+"\n")
                }
                Log.v(TAG, builder.toString());
            }
            Constant.REQUEST_CODE_TAKE_IMAGE -> if (resultCode == RESULT_OK) {


            }
        }
    }
}
