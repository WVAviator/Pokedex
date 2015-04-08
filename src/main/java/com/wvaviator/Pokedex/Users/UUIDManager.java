package com.wvaviator.Pokedex.Users;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import net.minecraft.entity.player.EntityPlayerMP;

import com.wvaviator.Pokedex.Database.Database;

public class UUIDManager {

	public static String getUsername(String uuid) throws SQLException {
		
		String query = "SELECT name FROM players WHERE uuid = '" + uuid + "'";
		Statement stmt = null;
		Connection c = Database.getConnection();
		
		try {
			
			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			String username = rs.getString("name").toUpperCase();

			return username;
			
		} finally {
			stmt.close();
			c.close();	
		}
		
	}
	
	public static boolean isInDatabase(EntityPlayerMP player) throws SQLException {
		
		String uuid = player.getUniqueID().toString();
		String query = "SELECT uuid FROM players WHERE uuid = '" + uuid + "'";
		
		Statement stmt = null;
		Connection c = Database.getConnection();
		
		try {
			
			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			if(!rs.next()) {
				return false;
			} else {
				return true;
			}
			
		} finally {
			stmt.close();
			c.close();
		}
		
	}
	
	public static void addPlayer(EntityPlayerMP player) throws SQLException {
		
		String uuid = player.getUniqueID().toString();
		String username = player.getName().toUpperCase();
		
		String update = "INSERT INTO players VALUES ('" + uuid + "', '" + username + "')";
		
		Statement stmt = null;
		Connection c = Database.getConnection();
		
		
		try {
			
			stmt = c.createStatement();
			stmt.executeUpdate(update);
			
		} finally {
			stmt.close();
			c.close();
			
		}
		
	}
	
	public static boolean didUsernameChange(EntityPlayerMP player) throws SQLException {
		
		String uuid = player.getUniqueID().toString();
		String username = getUsername(uuid);
		
		String nameToCheck = player.getName().toUpperCase();
		
		if (username.equalsIgnoreCase(nameToCheck)) {
			return false;
		} else {
			return true;
		}
		
	}
	
	public static void updateUsername(EntityPlayerMP player) throws SQLException {
		
		String uuid = player.getUniqueID().toString();
		String username = player.getName().toUpperCase();
		String update = "UPDATE players SET name = '" + username + "' WHERE  uuid = '" + uuid + "'";
		
		Statement stmt = null;
		Connection c = Database.getConnection();
		
		
		try {
			
			stmt = c.createStatement();
			stmt.executeUpdate(update);
			
		} finally {
			stmt.close();
			c.close();
			
		}
		
	}
	
}
