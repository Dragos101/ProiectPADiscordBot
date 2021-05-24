package commands.lavaplayer;

import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayerManager;

public class GuildMusicManager {
    public final AudioPlayer audioPlayer;
    public final TrackScheduler trackScheduler;
    public final AudioPlayerSendHandler audioPlayerSendHandler;

    public GuildMusicManager(AudioPlayerManager audioPlayerManager){
        this.audioPlayer = audioPlayerManager.createPlayer();
        this.trackScheduler = new TrackScheduler(this.audioPlayer);
        this.audioPlayer.addListener(this.trackScheduler);
        this.audioPlayerSendHandler = new AudioPlayerSendHandler(this.audioPlayer);
    }

    public AudioPlayerSendHandler getAudioPlayerSendHandler(){
        return audioPlayerSendHandler;
    }

}
