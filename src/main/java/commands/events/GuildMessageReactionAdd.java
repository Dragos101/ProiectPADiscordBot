package commands.events;

import net.dv8tion.jda.api.events.message.guild.react.GuildMessageReactionAddEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class GuildMessageReactionAdd extends ListenerAdapter {
    public void onGuildMessageReactionAdd(GuildMessageReactionAddEvent event){
        if(event.getReactionEmote().getName().equals("❌") && !event.getMember().getUser().equals(event.getJDA())){
            //daca mesajul e ❌ si cel care a trimis mesajul nu e botul
            if(event.getMember().getUser().equals(event.getChannel().retrieveMessageById(event.getMessageId()).complete().getAuthor())){
                //if the user that raect is the author of the message
                event.getChannel().retrieveMessageById(event.getMessageId()).complete().delete().queue();
            }else{
                //if it's not
                event.getReaction().removeReaction().queue();
            }
        }
    }


}
