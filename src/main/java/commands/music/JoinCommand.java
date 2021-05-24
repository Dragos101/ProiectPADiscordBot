package commands.music;

import commands.CommandContext;
import commands.ICommand;
import net.dv8tion.jda.api.entities.GuildVoiceState;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.VoiceChannel;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.managers.AudioManager;

import java.util.Arrays;
import java.util.List;

public class JoinCommand extends ListenerAdapter implements ICommand  {

    public void onGuildMessageReceived(GuildMessageReceivedEvent guildMessageReceivedEvent){
        List<String> args = Arrays.asList(guildMessageReceivedEvent.getMessage().getContentRaw().split("\\s+"));
        CommandContext commandContext = new CommandContext(guildMessageReceivedEvent, args);
        if(args.get(0).equalsIgnoreCase("~join")) handle(commandContext);
    }

    @Override
    public void handle(CommandContext commandContext) {
        final TextChannel channel = commandContext.getChannel();
        final Member self = commandContext.getSelfMember();
        final GuildVoiceState selfVoiceState = self.getVoiceState();

        assert selfVoiceState != null;
        if(selfVoiceState.inVoiceChannel()){
            channel.sendMessage("Sunt deja intr-un voice channel").queue();
            return;
        }

        final Member member = commandContext.getMember();
        final GuildVoiceState memberVoiceState = member.getVoiceState();

        if(!memberVoiceState.inVoiceChannel()){
            channel.sendMessage("Daca nu esti intr-un voice channel nu o sa mearga").queue();
            return;
        }

        final AudioManager audioManager = commandContext.getGuild().getAudioManager();
        final VoiceChannel memberChannel = memberVoiceState.getChannel();

        audioManager.openAudioConnection(memberChannel);
        channel.sendMessageFormat("Se conecteaza la %s", memberChannel.getName()).queue();
        return;
    }

    @Override
    public String getName() {
        return "join";
    }

    @Override
    public String getHelp() {
        return "Adauga bot-ul in voice channel";
    }
}
