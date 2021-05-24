package commands;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.awt.*;
import java.util.List;

public class Clear extends ListenerAdapter {//ALL COMMANDS SHOULD BE LISTENERS

    public void onGuildMessageReceived( GuildMessageReceivedEvent event) {
        String[] args = event.getMessage().getContentRaw().split("\\s+"); //counts all spaces between arguments

        if(args[0].equalsIgnoreCase(   "~" + "clear")){
            if(args.length < 2){
                //clear without args
                EmbedBuilder usage = new EmbedBuilder();
                usage.setColor(Color.red);
                usage.setTitle("🚫 Specify amount to delete.");
                usage.setDescription("Usage: '" + "~" + "clear [# of messages]'");
                event.getChannel().sendMessage(usage.build()).queue();

            }else{
                try { //because, on Discord, you only can delete just 100 messages(maximum)
                    List<Message> mess = event.getChannel().getHistory().retrievePast(Integer.parseInt(args[1])).complete();
                    event.getChannel().deleteMessages(mess).queue();

                    //success
                    EmbedBuilder success = new EmbedBuilder();
                    success.setColor(Color.red);
                    success.setTitle("✅ Successful deleted " + args[1] + " messages.");
                    event.getChannel().sendMessage(success.build()).queue();

                }catch (IllegalArgumentException e){
                    if(e.toString().startsWith("java.lang.IllegalArgumentException: Message retrieval")){
                        //Too many messages
                        EmbedBuilder error = new EmbedBuilder();
                        error.setColor(Color.red);
                        error.setTitle("🚫 Too many messages selected. 🚫");
                        error.setDescription("Between 1 to 100 messages can be deleted at one time.");
                        event.getChannel().sendMessage(error.build()).queue();
                    }else{
                        //too old
                        EmbedBuilder error = new EmbedBuilder();
                        error.setColor(Color.red);
                        error.setTitle("🚫 Selected messages are older than 2 weeks. 🚫");
                        error.setDescription("You cannot delete messages older than 2 weeks.");
                        event.getChannel().sendMessage(error.build()).queue();

                    }
                }

            }
        }
    }
}
