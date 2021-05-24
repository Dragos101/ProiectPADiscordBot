package commands.events;

import java.util.List;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;


public class GuildMessageReceived extends ListenerAdapter {
    public void onGuildMessageReceived(GuildMessageReceivedEvent event){
        String[] args = event.getMessage().getContentRaw().split("\\s+");

        if(args[0].equalsIgnoreCase("~mute")){
            if(args.length == 2){

                Guild guild = event.getGuild();
                List<net.dv8tion.jda.api.entities.Member> members = event.getMessage().getMentionedMembers();
                Role role = event.getGuild().getRoleById("845690381521649735");
                guild.addRoleToMember(members.get(0), role).queue();

                if(!members.get(0).getRoles().contains(role)){
                    //Mute user
                    event.getChannel().sendMessage("Muted " + members.get(0) + ".").queue();
                    event.getGuild().addRoleToMember(members.get(0), role).queue();
                }
                else {
                    //unmute user
                    event.getChannel().sendMessage("Unmuted " + members.get(0) + ".").queue();
                    event.getGuild().removeRoleFromMember(members.get(0), role).queue();
                }

            }
            else if(args.length == 3){

            }
            else{
                event.getChannel().sendMessage("Eroare de sintaxa. Foloseste ~mute [user mention] [time{optional}] ").queue();
            }
        }
    }

}