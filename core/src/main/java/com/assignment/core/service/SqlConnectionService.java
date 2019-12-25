package com.assignment.core.service;

import java.sql.Connection;

public interface SqlConnectionService {
	public Connection getConnection(String dataSourceName);
}
