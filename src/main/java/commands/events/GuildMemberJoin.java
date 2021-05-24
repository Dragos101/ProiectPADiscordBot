package commands.events;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.Random;

public class GuildMemberJoin extends ListenerAdapter {

    String[] messages = {
            "[member] a venit. Ce faci, informaticianule?",
            "Nu te lasa, [member]. Esti puternic, [member]!",
            "Hey! ASCULTA! A VENIIIIIIIIIT [member]!",
            "3,2,1, vine [member]!",
            "[member] e aici cu tine.",
    };

    public void onGuildMemberJoin(GuildMemberJoinEvent event) {
        Random rand = new Random();
        int number = rand.nextInt(messages.length);

        EmbedBuilder join = new EmbedBuilder();
        join.setColor(0x66d8ff);
        join.setDescription(messages[number].replace("[member]", event.getMember().getAsMention()));


        event.getGuild().getDefaultChannel().sendMessage(join.build()).queue();

        // Add role
        //event.getGuild().modifyMemberRoles(event.getMember(), event.getGuild().getRoleById(840671948835913769L)).queue();
    }
}
