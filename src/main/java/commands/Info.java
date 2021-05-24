package commands;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class Info extends ListenerAdapter {
    public void onGuildMessageReceived(GuildMessageReceivedEvent event) { //register anytime when an event occurs
        String[] args = event.getMessage().getContentRaw().split("\\s+");

        if(args[0].equalsIgnoreCase("~" + "info")){ //our first command
            //Embeds
            EmbedBuilder info = new EmbedBuilder();
            info.setTitle("💻 Bot info 💻");
            //create a field
            info.addField("Creator", "toolbox", false);
            info.setDescription("Info about our bot Project PA");
            info.setColor(0xa08cde);
            info.setFooter("Created by Cezarus", event.getMember().getUser().getAvatarUrl());


            event.getChannel().sendTyping().queue();
            event.getChannel().sendMessage(info.build()).queue();
            info.clear();//for not occupying space on the queue
        }
    }
}
