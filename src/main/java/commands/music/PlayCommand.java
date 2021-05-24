package commands.music;

import commands.CommandContext;
import commands.ICommand;
import commands.lavaplayer.PlayerManager;
import net.dv8tion.jda.api.entities.GuildVoiceState;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.Arrays;
import java.util.List;

public class PlayCommand extends ListenerAdapter implements ICommand {

    public void onGuildMessageReceived(GuildMessageReceivedEvent guildMessageReceivedEvent){
        List<String> args = Arrays.asList(guildMessageReceivedEvent.getMessage().getContentRaw().split("\\s+"));
        CommandContext commandContext = new CommandContext(guildMessageReceivedEvent, args);
        if(args.get(0).equalsIgnoreCase("~play")) handle(commandContext);
    }

    @Override
    public void handle(CommandContext commandContext) {
        final TextChannel textChannel = commandContext.getChannel();
        final Member self = commandContext.getSelfMember();
        final GuildVoiceState selfVoiceState = self.getVoiceState();

        if(!selfVoiceState.inVoiceChannel()){
            textChannel.sendMessage("Nu sunt intr-un voice channel");
            return;
        }

        final Member member = commandContext.getMember();
        final GuildVoiceState memberVoiceState = member.getVoiceState();

        if(!memberVoiceState.inVoiceChannel()){
            textChannel.sendMessage("Nu esti intr-un voice channel.");
            return;
        }

        if(!memberVoiceState.getChannel().equals(selfVoiceState.getChannel())){
            textChannel.sendMessage("Nu esti in acelasi voice channel");
            return;
        }

        PlayerManager.getInstance().loadAndPlay(textChannel, "https://www.youtube.com/watch?v=yAKCe2z4P14");

    }

    @Override
    public String getName() {
        return "play";
    }

    @Override
    public String getHelp() {
        return "Primeste o melodie ca input si o reda daca este intr-un voice channel";
    }
}
