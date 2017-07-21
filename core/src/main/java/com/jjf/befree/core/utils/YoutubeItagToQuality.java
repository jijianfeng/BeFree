package com.jjf.befree.core.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;

/**
 * Created by jjf_lenovo on 2017/5/16.
 */
public class YoutubeItagToQuality {

    private static String itags;

    static {
        itags = "{\n" +
                "  \"5\": {\n" +
                "    \"description\": \"(Basic Youtube Default)\",\n" +
                "    \"container\": \"flv\",\n" +
                "    \"resolution\": \"400 x 240\",\n" +
                "    \"frame_rate\": \"25 fps\",\n" +
                "    \"video_format\": \"FLV1 H.263\",\n" +
                "    \"audio\": \"Stereo, 22.05 KHz 64.0 Kbps\",\n" +
                "    \"audio_format\": \"MP3 (MPEG Audio)\",\n" +
                "    \"type\": \"Main Stream\"\n" +
                "  },\n" +
                "  \"6\": {\n" +
                "    \"container\": \"flv\",\n" +
                "    \"resolution\": \"450 x 270\",\n" +
                "    \"type\": \"Main Stream\"\n" +
                "  },\n" +
                "  \"13\": {\n" +
                "    \"description\": \"(Mobile phones, iPod friendly)\",\n" +
                "    \"container\": \"3gp\",\n" +
                "    \"type\": \"Main Stream\"\n" +
                "  },\n" +
                "  \"17\": {\n" +
                "    \"description\": \"Low Quality [144p]\",\n" +
                "    \"container\": \"3gp\",\n" +
                "    \"resolution\": \"176 x 144\",\n" +
                "    \"frame_rate\": \"12 fps\",\n" +
                "    \"video_format\": \"MPEG-4\",\n" +
                "    \"audio\": \"Mono 22.05 KHz 24.0 - 25.6 Kbps\",\n" +
                "    \"audio_format\": \"AAC\",\n" +
                "    \"type\": \"Main Stream\"\n" +
                "  },\n" +
                "  \"18\": {\n" +
                "    \"description\": \"Medium Quality [360p]\",\n" +
                "    \"container\": \"mp4\",\n" +
                "    \"resolution\": \"640 x 360\",\n" +
                "    \"frame_rate\": \"25 fps\",\n" +
                "    \"video_format\": \"AVC (MPEG4 H.264)\",\n" +
                "    \"audio\": \"Stereo, 44.1 KHz 96.0 - 100 Kbps\",\n" +
                "    \"audio_format\": \"AAC\",\n" +
                "    \"type\": \"Main Stream\"\n" +
                "  },\n" +
                "  \"22\": {\n" +
                "    \"description\": \"HD High Quality [720p]\",\n" +
                "    \"container\": \"mp4\",\n" +
                "    \"resolution\": \"1280 x 720\",\n" +
                "    \"frame_rate\": \"25 fps\",\n" +
                "    \"video_format\": \"AVC (MPEG4 H.264)\",\n" +
                "    \"audio\": \"Stereo, 44.1 KHz 192.0 - 201 Kbps\",\n" +
                "    \"audio_format\": \"AAC\",\n" +
                "    \"type\": \"Main Stream\"\n" +
                "  },\n" +
                "  \"34\": {\n" +
                "    \"description\": \"Medium Quality [360p]\",\n" +
                "    \"container\": \"flv\",\n" +
                "    \"resolution\": \"640 x 360\",\n" +
                "    \"type\": \"Main Stream\"\n" +
                "  },\n" +
                "  \"35\": {\n" +
                "    \"description\": \"High Quality [480p]\",\n" +
                "    \"container\": \"flv\",\n" +
                "    \"resolution\": \"854 x 480\",\n" +
                "    \"type\": \"Main Stream\"\n" +
                "  },\n" +
                "  \"36\": {\n" +
                "    \"description\": \"Low Quality [240p]\",\n" +
                "    \"container\": \"3gp\",\n" +
                "    \"resolution\": \"320 x 240\",\n" +
                "    \"frame_rate\": \"25 fps\",\n" +
                "    \"video_format\": \"MPEG4 H.263\",\n" +
                "    \"audio\": \"Mono, 22.05 KHz 32.0 - 34.2 Kbps\",\n" +
                "    \"audio_format\": \"AAC\",\n" +
                "    \"type\": \"Main Stream\"\n" +
                "  },\n" +
                "  \"37\": {\n" +
                "    \"description\": \"HD High Quality [1080p]\",\n" +
                "    \"container\": \"mp4\",\n" +
                "    \"resolution\": \"1920 x 1080\",\n" +
                "    \"type\": \"Main Stream\"\n" +
                "  },\n" +
                "  \"38\": {\n" +
                "    \"description\": \"HD High Quality [3072p]\",\n" +
                "    \"container\": \"mp4\",\n" +
                "    \"resolution\": \"4096 x 3072\",\n" +
                "    \"type\": \"Main Stream\"\n" +
                "  },\n" +
                "  \"43\": {\n" +
                "    \"description\": \"Medium Quality [360p]\",\n" +
                "    \"container\": \"webm\",\n" +
                "    \"resolution\": \"640 x 360\",\n" +
                "    \"frame_rate\": \"24.194 fps\",\n" +
                "    \"video_format\": \"VP8\",\n" +
                "    \"audio\": \"Stereo, 44.1 KHz 128 Kbps\",\n" +
                "    \"audio_format\": \"Vorbis\",\n" +
                "    \"type\": \"Main Stream\"\n" +
                "  },\n" +
                "  \"44\": {\n" +
                "    \"description\": \"High Quality [480p]\",\n" +
                "    \"container\": \"webm\",\n" +
                "    \"resolution\": \"854 x 480\",\n" +
                "    \"type\": \"Main Stream\"\n" +
                "  },\n" +
                "  \"45\": {\n" +
                "    \"description\": \"HD High Quality [720p]\",\n" +
                "    \"container\": \"webm\",\n" +
                "    \"resolution\": \"1280 x 720\",\n" +
                "    \"type\": \"Main Stream\"\n" +
                "  },\n" +
                "  \"46\": {\n" +
                "    \"description\": \"HD High Quality [1080p]\",\n" +
                "    \"container\": \"webm\",\n" +
                "    \"resolution\": \"1920 x 1080\",\n" +
                "    \"type\": \"Main Stream\"\n" +
                "  },\n" +
                "  \"59\": {\n" +
                "    \"description\": \"High Quality [480p]\",\n" +
                "    \"container\": \"mp4\",\n" +
                "    \"resolution\": \"854 x 480\",\n" +
                "    \"frame_rate\": \"24.194 fps\",\n" +
                "    \"video_format\": \"AVC (MPEG4 H.264)\",\n" +
                "    \"audio\": \"Stereo, 44.1 KHz 128 Kbps\",\n" +
                "    \"audio_format\": \"AAC\",\n" +
                "    \"type\": \"Main Stream\"\n" +
                "  },\n" +
                "  \"78\": {\n" +
                "    \"description\": \"High Quality [480p]\",\n" +
                "    \"container\": \"mp4\",\n" +
                "    \"resolution\": \"854 x 480\",\n" +
                "    \"frame_rate\": \"24.194 fps\",\n" +
                "    \"video_format\": \"AVC (MPEG4 H.264)\",\n" +
                "    \"audio\": \"Stereo, 44.1 KHz 128 Kbps\",\n" +
                "    \"audio_format\": \"AAC\",\n" +
                "    \"type\": \"Main Stream\"\n" +
                "  },\n" +
                "  \"82\": {\n" +
                "    \"container\": \"mp4\",\n" +
                "    \"description\": \"Medium Quality [360p][3D]\",\n" +
                "    \"resolution\": \"640 x 360 [3D]\",\n" +
                "    \"frame_rate\": \"25 fps\",\n" +
                "    \"video_format\": \"AVC (MPEG4 H.264)\",\n" +
                "    \"audio\": \"Stereo, 44.1 KHz 128 - 134 Kbps\",\n" +
                "    \"audio_format\": \"AAC\",\n" +
                "    \"type\": \"3d Videos\"\n" +
                "  },\n" +
                "  \"83\": {\n" +
                "    \"container\": \"mp4\",\n" +
                "    \"description\": \"High Quality [480p][3D]\",\n" +
                "    \"resolution\": \"854 x 480 [3D]\",\n" +
                "    \"type\": \"3d Videos\"\n" +
                "  },\n" +
                "  \"84\": {\n" +
                "    \"container\": \"mp4\",\n" +
                "    \"description\": \"HD High Quality [720p][3D]\",\n" +
                "    \"resolution\": \"1280 x 720 [3D]\",\n" +
                "    \"frame_rate\": \"25 fps\",\n" +
                "    \"video_format\": \"AVC (MPEG4 H.264)\",\n" +
                "    \"audio\": \"Stereo, 44.1 KHz 192 - 201 Kbps\",\n" +
                "    \"audio_format\": \"AAC\",\n" +
                "    \"type\": \"3d Videos\"\n" +
                "  },\n" +
                "  \"85\": {\n" +
                "    \"container\": \"mp4\",\n" +
                "    \"description\": \"HD High Quality [1080p][3D]\",\n" +
                "    \"resolution\": \"1920 x 1080p [3D]\",\n" +
                "    \"type\": \"3d Videos\"\n" +
                "  },\n" +
                "  \"91\": {\n" +
                "    \"container\": \"mp4\",\n" +
                "    \"description\": \"Low Quality [144p]\",\n" +
                "    \"resolution\": \"176 x 144\",\n" +
                "    \"streaming_protocol\": \"HLS\",\n" +
                "    \"video_format\": \"AVC (MPEG4 H.264)\",\n" +
                "    \"audio_format\": \"AAC\",\n" +
                "    \"audio\": \"Stereo, 44.1 KHz 48 Kbps\",\n" +
                "    \"type\": \"Apple HTTP Live Streaming (HLS)\"\n" +
                "  },\n" +
                "  \"92\": {\n" +
                "    \"container\": \"mp4\",\n" +
                "    \"description\": \"Low Quality [240p]\",\n" +
                "    \"resolution\": \"320 x 240\",\n" +
                "    \"streaming_protocol\": \"HLS\",\n" +
                "    \"type\": \"Apple HTTP Live Streaming (HLS)\"\n" +
                "  },\n" +
                "  \"93\": {\n" +
                "    \"container\": \"mp4\",\n" +
                "    \"description\": \"Medium Quality [360p]\",\n" +
                "    \"resolution\": \"640 x 360\",\n" +
                "    \"streaming_protocol\": \"HLS\",\n" +
                "    \"type\": \"Apple HTTP Live Streaming (HLS)\"\n" +
                "  },\n" +
                "  \"94\": {\n" +
                "    \"container\": \"mp4\",\n" +
                "    \"description\": \"High Quality [480p]\",\n" +
                "    \"resolution\": \"854 x 480\",\n" +
                "    \"streaming_protocol\": \"HLS\",\n" +
                "    \"type\": \"Apple HTTP Live Streaming (HLS)\"\n" +
                "  },\n" +
                "  \"95\": {\n" +
                "    \"container\": \"mp4\",\n" +
                "    \"description\": \"HD High Quality [720p]\",\n" +
                "    \"resolution\": \"1280 x 720\",\n" +
                "    \"streaming_protocol\": \"HLS\",\n" +
                "    \"type\": \"Apple HTTP Live Streaming (HLS)\"\n" +
                "  },\n" +
                "  \"96\": {\n" +
                "    \"container\": \"mp4\",\n" +
                "    \"description\": \"HD High Quality [1080p]\",\n" +
                "    \"resolution\": \"1920 x 1080\",\n" +
                "    \"streaming_protocol\": \"HLS\",\n" +
                "    \"type\": \"Apple HTTP Live Streaming (HLS)\"\n" +
                "  },\n" +
                "  \"100\": {\n" +
                "    \"container\": \"webm\",\n" +
                "    \"description\": \"Medium Quality [360p][3D]\",\n" +
                "    \"resolution\": \"640 x 360 [3D]\",\n" +
                "    \"frame_rate\": \"24.194 fps\",\n" +
                "    \"video_format\": \"VP8\",\n" +
                "    \"audio\": \"Stereo, 44.1 KHz 128 Kbps\",\n" +
                "    \"audio_format\": \"AAC\",\n" +
                "    \"type\": \"3d Videos\"\n" +
                "  },\n" +
                "  \"101\": {\n" +
                "    \"container\": \"webm\",\n" +
                "    \"description\": \"High Quality [480p][3D]\",\n" +
                "    \"resolution\": \"854 x 480 [3D]\",\n" +
                "    \"type\": \"3d Videos\"\n" +
                "  },\n" +
                "  \"102\": {\n" +
                "    \"container\": \"webm\",\n" +
                "    \"description\": \"HD High Quality [720p][3D]\",\n" +
                "    \"resolution\": \"1280 x 720 [3D]\",\n" +
                "    \"type\": \"3d Videos\"\n" +
                "  },\n" +
                "  \"120\": {\n" +
                "    \"description\": \"HD High Quality [720p]\",\n" +
                "    \"container\": \"flv\",\n" +
                "    \"resolution\": \"1280 x 720\",\n" +
                "    \"video_format\": \"H.264\",\n" +
                "    \"audio\": \"Stereo, 22.05 KHz 128 Kbps\",\n" +
                "    \"audio_format\": \"AAC\",\n" +
                "    \"type\": \"Main Stream\"\n" +
                "  },\n" +
                "  \"127\": {\n" +
                "    \"container\": \"ts\",\n" +
                "    \"audio\": \"Stereo, 44.1 KHz 96.0 Kbps\",\n" +
                "    \"audio_format\": \"AAC [DASH audio]\",\n" +
                "    \"video_format\": null,\n" +
                "    \"type\": \"Dash TS Audio\"\n" +
                "  },\n" +
                "  \"128\": {\n" +
                "    \"container\": \"ts\",\n" +
                "    \"audio\": \"Stereo, 44.1 KHz 96.0 Kbps\",\n" +
                "    \"audio_format\": \"AAC [DASH audio]\",\n" +
                "    \"video_format\": null,\n" +
                "    \"type\": \"Dash TS Audio\"\n" +
                "  },\n" +
                "  \"132\": {\n" +
                "    \"container\": \"mp4\",\n" +
                "    \"description\": \"Low Quality [240p]\",\n" +
                "    \"resolution\": \"320 x 240\",\n" +
                "    \"streaming_protocol\": \"HLS\",\n" +
                "    \"type\": \"Apple HTTP Live Streaming (HLS)\"\n" +
                "  },\n" +
                "  \"133\": {\n" +
                "    \"container\": \"mp4\",\n" +
                "    \"resolution\": \"320 x 240\",\n" +
                "    \"video_type\": \"DASH video\",\n" +
                "    \"audio\": null,\n" +
                "    \"type\": \"DASH MP4 video\"\n" +
                "  },\n" +
                "  \"134\": {\n" +
                "    \"container\": \"mp4\",\n" +
                "    \"resolution\": \"640 x 360\",\n" +
                "    \"video_type\": \"DASH video\",\n" +
                "    \"audio\": null,\n" +
                "    \"type\": \"DASH MP4 video\"\n" +
                "  },\n" +
                "  \"135\": {\n" +
                "    \"container\": \"mp4\",\n" +
                "    \"resolution\": \"854 x 480\",\n" +
                "    \"video_type\": \"DASH video\",\n" +
                "    \"audio\": null,\n" +
                "    \"type\": \"DASH MP4 video\"\n" +
                "  },\n" +
                "  \"136\": {\n" +
                "    \"container\": \"mp4\",\n" +
                "    \"resolution\": \"1280 x 720\",\n" +
                "    \"video_type\": \"DASH video\",\n" +
                "    \"audio\": null,\n" +
                "    \"type\": \"DASH MP4 video\"\n" +
                "  },\n" +
                "  \"137\": {\n" +
                "    \"container\": \"mp4\",\n" +
                "    \"resolution\": \"1920 x 1080\",\n" +
                "    \"video_type\": \"DASH video\",\n" +
                "    \"audio\": null,\n" +
                "    \"type\": \"DASH MP4 video\"\n" +
                "  },\n" +
                "  \"138\": {\n" +
                "    \"container\": \"mp4\",\n" +
                "    \"resolution\": \"* x 2160 (not fix)\",\n" +
                "    \"video_type\": \"DASH video\",\n" +
                "    \"audio\": null,\n" +
                "    \"type\": \"DASH MP4 video\"\n" +
                "  },\n" +
                "  \"139\": {\n" +
                "    \"container\": \"m4a\",\n" +
                "    \"audio\": \"Stereo, 44.1 KHz 48 Kbps\",\n" +
                "    \"audio_format\": \"AAC [DASH audio]\",\n" +
                "    \"video_format\": null,\n" +
                "    \"type\": \"Dash MP4 Audio\"\n" +
                "  },\n" +
                "  \"140\": {\n" +
                "    \"container\": \"m4a\",\n" +
                "    \"audio\": \"Stereo, 44.1 KHz 128 Kbps\",\n" +
                "    \"audio_format\": \"AAC [DASH audio]\",\n" +
                "    \"video_format\": null,\n" +
                "    \"type\": \"Dash MP4 Audio\"\n" +
                "  },\n" +
                "  \"141\": {\n" +
                "    \"container\": \"m4a\",\n" +
                "    \"audio\": \"Stereo, 44.1 KHz 256 Kbps\",\n" +
                "    \"audio_format\": \"AAC [DASH audio]\",\n" +
                "    \"video_format\": null,\n" +
                "    \"type\": \"Dash MP4 Audio\"\n" +
                "  },\n" +
                "  \"151\": {\n" +
                "    \"container\": \"mp4\",\n" +
                "    \"resolution\": \"* x 72\",\n" +
                "    \"streaming_protocol\": \"HLS\",\n" +
                "    \"type\": \"Apple HTTP Live Streaming (HLS)\"\n" +
                "  },\n" +
                "  \"160\": {\n" +
                "    \"container\": \"mp4\",\n" +
                "    \"resolution\": \"176 x 144\",\n" +
                "    \"video_type\": \"DASH video\",\n" +
                "    \"audio\": null,\n" +
                "    \"type\": \"DASH MP4 video\"\n" +
                "  },\n" +
                "  \"167\": {\n" +
                "    \"container\": \"webm\",\n" +
                "    \"resolution\": \"640 x 360\",\n" +
                "    \"video_type\": \"VP8 DASH video\",\n" +
                "    \"audio\": null,\n" +
                "    \"type\": \"Dash WEBM Video\"\n" +
                "  },\n" +
                "  \"168\": {\n" +
                "    \"container\": \"webm\",\n" +
                "    \"resolution\": \"854 x 480\",\n" +
                "    \"video_type\": \"VP8 DASH video\",\n" +
                "    \"audio\": null,\n" +
                "    \"type\": \"Dash WEBM Video\"\n" +
                "  },\n" +
                "  \"169\": {\n" +
                "    \"container\": \"webm\",\n" +
                "    \"resolution\": \"1280 x 720\",\n" +
                "    \"video_type\": \"VP8 DASH video\",\n" +
                "    \"audio\": null,\n" +
                "    \"type\": \"Dash WEBM Video\"\n" +
                "  },\n" +
                "  \"170\": {\n" +
                "    \"container\": \"webm\",\n" +
                "    \"resolution\": \"1920 x 1080\",\n" +
                "    \"video_type\": \"VP8 DASH video\",\n" +
                "    \"audio\": null,\n" +
                "    \"type\": \"Dash WEBM Video\"\n" +
                "  },\n" +
                "  \"171\": {\n" +
                "    \"container\": \"webm\",\n" +
                "    \"audio\": \"Stereo, 44.1 KHz 128 Kbps\",\n" +
                "    \"audio_format\": \"Vorbis [DASH audio]\",\n" +
                "    \"video_format\": null,\n" +
                "    \"type\": \"Dash WEBM Audio\"\n" +
                "  },\n" +
                "  \"172\": {\n" +
                "    \"container\": \"webm\",\n" +
                "    \"audio\": \"Stereo, 44.1 KHz 256 Kbps\",\n" +
                "    \"audio_format\": \"AAC [DASH audio]\",\n" +
                "    \"video_format\": null,\n" +
                "    \"type\": \"Dash WEBM Audio\"\n" +
                "  },\n" +
                "  \"212\": {\n" +
                "    \"container\": \"mp4\",\n" +
                "    \"resolution\": \"854 x 480\",\n" +
                "    \"video_format\": \"AVC (MPEG4 H.264)\",\n" +
                "    \"video_type\": \"DASH video\",\n" +
                "    \"audio\": null,\n" +
                "    \"type\": \"DASH MP4 video\"\n" +
                "  },\n" +
                "  \"218\": {\n" +
                "    \"container\": \"webm\",\n" +
                "    \"resolution\": \"854 x 480\",\n" +
                "    \"video_type\": \"VP8 DASH video\",\n" +
                "    \"audio\": null,\n" +
                "    \"type\": \"Dash WEBM Video\"\n" +
                "  },\n" +
                "  \"219\": {\n" +
                "    \"container\": \"webm\",\n" +
                "    \"resolution\": \"* x 144\",\n" +
                "    \"video_type\": \"VP9 DASH video\",\n" +
                "    \"audio\": null,\n" +
                "    \"type\": \"Dash WEBM Video\"\n" +
                "  },\n" +
                "  \"242\": {\n" +
                "    \"container\": \"webm\",\n" +
                "    \"resolution\": \"320 x 240\",\n" +
                "    \"video_type\": \"VP8 DASH video\",\n" +
                "    \"audio\": null,\n" +
                "    \"type\": \"Dash WEBM Video\"\n" +
                "  },\n" +
                "  \"243\": {\n" +
                "    \"container\": \"webm\",\n" +
                "    \"resolution\": \"640 x 360\",\n" +
                "    \"video_type\": \"VP8 DASH video\",\n" +
                "    \"audio\": null,\n" +
                "    \"type\": \"Dash WEBM Video\"\n" +
                "  },\n" +
                "  \"244\": {\n" +
                "    \"container\": \"webm\",\n" +
                "    \"resolution\": \"854 x 480\",\n" +
                "    \"video_type\": \"VP8 DASH video\",\n" +
                "    \"audio\": null,\n" +
                "    \"type\": \"Dash WEBM Video\"\n" +
                "  },\n" +
                "  \"245\": {\n" +
                "    \"container\": \"webm\",\n" +
                "    \"resolution\": \"854 x 480\",\n" +
                "    \"video_type\": \"VP8 DASH video\",\n" +
                "    \"audio\": null,\n" +
                "    \"type\": \"Dash WEBM Video\"\n" +
                "  },\n" +
                "  \"246\": {\n" +
                "    \"container\": \"webm\",\n" +
                "    \"resolution\": \"854 x 480\",\n" +
                "    \"video_type\": \"VP8 DASH video\",\n" +
                "    \"audio\": null,\n" +
                "    \"type\": \"Dash WEBM Video\"\n" +
                "  },\n" +
                "  \"247\": {\n" +
                "    \"container\": \"webm\",\n" +
                "    \"resolution\": \"1280 x 720\",\n" +
                "    \"video_type\": \"VP8 DASH video\",\n" +
                "    \"audio\": null,\n" +
                "    \"type\": \"Dash WEBM Video\"\n" +
                "  },\n" +
                "  \"248\": {\n" +
                "    \"container\": \"webm\",\n" +
                "    \"resolution\": \"1920 x 1080\",\n" +
                "    \"video_type\": \"VP8 DASH video\",\n" +
                "    \"audio\": null,\n" +
                "    \"type\": \"Dash WEBM Video\"\n" +
                "  },\n" +
                "  \"249\": {\n" +
                "    \"container\": \"webm\",\n" +
                "    \"audio\": \"Stereo, 44.1 KHz 50 Kbps\",\n" +
                "    \"audio_format\": \"OPUS [DASH audio]\",\n" +
                "    \"video_format\": null,\n" +
                "    \"type\": \"Dash WEBM Audio\"\n" +
                "  },\n" +
                "  \"250\": {\n" +
                "    \"container\": \"webm\",\n" +
                "    \"audio\": \"Stereo, 44.1 KHz 70 Kbps\",\n" +
                "    \"audio_format\": \"OPUS [DASH audio]\",\n" +
                "    \"video_format\": null,\n" +
                "    \"type\": \"Dash WEBM Audio\"\n" +
                "  },\n" +
                "  \"251\": {\n" +
                "    \"container\": \"webm\",\n" +
                "    \"audio\": \"Stereo, 44.1 KHz 160 Kbps\",\n" +
                "    \"audio_format\": \"OPUS [DASH audio]\",\n" +
                "    \"video_format\": null,\n" +
                "    \"type\": \"Dash WEBM Audio\"\n" +
                "  },\n" +
                "  \"256\": {\n" +
                "    \"container\": \"m4a\",\n" +
                "    \"audio_format\": \"AAC [DASH audio]\",\n" +
                "    \"video_format\": null,\n" +
                "    \"type\": \"Dash MP4 Audio\"\n" +
                "  },\n" +
                "  \"258\": {\n" +
                "    \"container\": \"m4a\",\n" +
                "    \"audio_format\": \"AAC [DASH audio]\",\n" +
                "    \"video_format\": null,\n" +
                "    \"type\": \"Dash MP4 Audio\"\n" +
                "  },\n" +
                "  \"264\": {\n" +
                "    \"container\": \"mp4\",\n" +
                "    \"resolution\": \"176 x 1440\",\n" +
                "    \"video_type\": \"DASH video\",\n" +
                "    \"audio\": null,\n" +
                "    \"type\": \"DASH MP4 video\"\n" +
                "  },\n" +
                "  \"266\": {\n" +
                "    \"container\": \"mp4\",\n" +
                "    \"resolution\": \"* x 2160\",\n" +
                "    \"frame_rate\": \"60 fps\",\n" +
                "    \"video_type\": \"DASH video H.264\",\n" +
                "    \"audio\": null,\n" +
                "    \"type\": \"DASH MP4 video\"\n" +
                "  },\n" +
                "  \"271\": {\n" +
                "    \"container\": \"webm\",\n" +
                "    \"resolution\": \"176 x 1440\",\n" +
                "    \"video_type\": \"VP8 DASH video\",\n" +
                "    \"audio\": null,\n" +
                "    \"type\": \"Dash WEBM Video\"\n" +
                "  },\n" +
                "  \"272\": {\n" +
                "    \"container\": \"webm\",\n" +
                "    \"resolution\": \"* x 2160\",\n" +
                "    \"video_type\": \"VP8 DASH video\",\n" +
                "    \"audio\": null,\n" +
                "    \"type\": \"Dash WEBM Video\"\n" +
                "  },\n" +
                "  \"278\": {\n" +
                "    \"container\": \"webm\",\n" +
                "    \"resolution\": \"* x 144\",\n" +
                "    \"video_type\": \"VP9 DASH video\",\n" +
                "    \"audio\": null,\n" +
                "    \"type\": \"Dash WEBM Video\"\n" +
                "  },\n" +
                "  \"298\": {\n" +
                "    \"container\": \"mp4\",\n" +
                "    \"resolution\": \"1280 x 720\",\n" +
                "    \"frame_rate\": \"60 fps\",\n" +
                "    \"video_type\": \"DASH video H.264\",\n" +
                "    \"audio\": null,\n" +
                "    \"type\": \"DASH MP4 video\"\n" +
                "  },\n" +
                "  \"299\": {\n" +
                "    \"container\": \"mp4\",\n" +
                "    \"resolution\": \"1920 x 1080\",\n" +
                "    \"frame_rate\": \"60 fps\",\n" +
                "    \"video_type\": \"DASH video H.264\",\n" +
                "    \"audio\": null,\n" +
                "    \"type\": \"DASH MP4 video\"\n" +
                "  },\n" +
                "  \"302\": {\n" +
                "    \"container\": \"webm\",\n" +
                "    \"resolution\": \"* x 2160\",\n" +
                "    \"frame_rate\": \"60 fps\",\n" +
                "    \"video_type\": \"VP9 DASH video\",\n" +
                "    \"audio\": null,\n" +
                "    \"type\": \"Dash WEBM Video\"\n" +
                "  },\n" +
                "  \"303\": {\n" +
                "    \"container\": \"webm\",\n" +
                "    \"resolution\": \"1920 x 1080\",\n" +
                "    \"frame_rate\": \"60 fps\",\n" +
                "    \"video_type\": \"VP9 DASH video\",\n" +
                "    \"audio\": null,\n" +
                "    \"type\": \"Dash WEBM Video\"\n" +
                "  },\n" +
                "  \"308\": {\n" +
                "    \"container\": \"webm\",\n" +
                "    \"resolution\": \"176 x 1440\",\n" +
                "    \"frame_rate\": \"60 fps\",\n" +
                "    \"video_type\": \"VP9 DASH video\",\n" +
                "    \"audio\": null,\n" +
                "    \"type\": \"Dash WEBM Video\"\n" +
                "  },\n" +
                "  \"313\": {\n" +
                "    \"container\": \"webm\",\n" +
                "    \"resolution\": \"* x 2160\",\n" +
                "    \"frame_rate\": \"60 fps\",\n" +
                "    \"video_type\": \"VP9 DASH video\",\n" +
                "    \"audio\": null,\n" +
                "    \"type\": \"Dash WEBM Video\"\n" +
                "  },\n" +
                "  \"315\": {\n" +
                "    \"container\": \"webm\",\n" +
                "    \"resolution\": \"* x 2160\",\n" +
                "    \"frame_rate\": \"60 fps\",\n" +
                "    \"video_type\": \"VP9 DASH video\",\n" +
                "    \"audio\": null,\n" +
                "    \"type\": \"Dash WEBM Video\"\n" +
                "  },\n" +
                "  \"325\": {\n" +
                "    \"container\": \"m4a\",\n" +
                "    \"audio_format\": \"DTSE [DASH audio]\",\n" +
                "    \"video_format\": null,\n" +
                "    \"type\": \"Dash MP4 Audio\"\n" +
                "  },\n" +
                "  \"328\": {\n" +
                "    \"container\": \"m4a\",\n" +
                "    \"audio_format\": \"EC-3 [DASH audio]\",\n" +
                "    \"video_format\": null,\n" +
                "    \"type\": \"Dash MP4 Audio\"\n" +
                "  }\n" +
                "}";
    }

    /**
     * 根据youtube视频的itag获取视频分辨率，不包音乐
     * @param itag
     * @return
     */
    public static String getResolutionByItag(int itag){
        JSONObject object = JSON.parseObject(itags);
        JSONObject args = object.getJSONObject(String.valueOf(itag));
        if(!StringUtils.isEmpty(args.getString("resolution"))){
            return args.getString("resolution");
        }
        return "";
    }

    /**
     * 根据youtube视频的itag获取视频格式，不包音乐
     * @param itag
     * @return
     */
    public static String getTypeByItag(int itag){
        JSONObject object = JSON.parseObject(itags);
        JSONObject args = object.getJSONObject(String.valueOf(itag));
        if(!StringUtils.isEmpty(args.getString("container"))){
            return args.getString("container");
        }
        return "";
    }
}
