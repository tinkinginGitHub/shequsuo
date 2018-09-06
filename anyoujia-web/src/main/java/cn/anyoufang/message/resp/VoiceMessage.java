package cn.anyoufang.message.resp;

public class VoiceMessage extends BaseMessage {

    // 语音
    private Voice Voice;

    public Voice getVoice() {
        return Voice;
    }

    public void setVoice(Voice voice) {
        Voice = voice;
    }
}
