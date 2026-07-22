package Internship;



	import javax.swing.*;
	import javax.swing.table.DefaultTableModel;
	import java.awt.*;
	import java.sql.Connection;
	import java.sql.PreparedStatement;
	import java.sql.ResultSet;

	public class Dashboard extends JFrame {

	    JTable table;
	    DefaultTableModel model;
	    int maleCount = 0, femaleCount = 0;

	    public Dashboard() {
	        setTitle("Student Dashboard");
	        setSize(900, 600);
	        setLayout(new BorderLayout());
	        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	        // --------- Top Heading ----------
	        JLabel heading = new JLabel("Student Dashboard", JLabel.CENTER);
	        heading.setFont(new Font("Arial", Font.BOLD, 24));
	        heading.setForeground(new Color(75, 0, 130)); // Violet
	        add(heading, BorderLayout.NORTH);

	        // --------- Table ----------
	        String[] columns = {"ID", "Name", "Email", "Gender"};
	        model = new DefaultTableModel(columns, 0);
	        table = new JTable(model);
	        JScrollPane tableScroll = new JScrollPane(table);
	        tableScroll.setPreferredSize(new Dimension(400, 200));

	        // --------- Graph Panel ----------
	        JPanel graphPanel = new JPanel() {
	            @Override
	            protected void paintComponent(Graphics g) {
	                super.paintComponent(g);

	                int total = maleCount + femaleCount;
	                if (total == 0) return;

	                int maleBarHeight = (maleCount * 200) / total;
	                int femaleBarHeight = (femaleCount * 200) / total;

	                // Draw Axis
	                g.drawLine(50, 300, 300, 300); // X-axis
	                g.drawLine(50, 100, 50, 300);  // Y-axis

	                // Male Bar
	                g.setColor(Color.BLUE);
	                g.fillRect(80, 300 - maleBarHeight, 80, maleBarHeight);
	                g.setColor(Color.BLACK);
	                g.drawString("Male (" + maleCount + ")", 90, 320);

	                // Female Bar
	                g.setColor(Color.PINK);
	                g.fillRect(180, 300 - femaleBarHeight, 80, femaleBarHeight);
	                g.setColor(Color.BLACK);
	                g.drawString("Female (" + femaleCount + ")", 185, 320);
	            }
	        };
	        graphPanel.setPreferredSize(new Dimension(400, 400));
	        graphPanel.setBackground(new Color(240, 240, 240));

	        // --------- Split Panels ----------
	        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, tableScroll, graphPanel);
	        splitPane.setDividerLocation(450);

	        add(splitPane, BorderLayout.CENTER);

	        // Load data from database
	        loadData();

	        setVisible(true);
	    }

	    private void loadData() {
	        try (Connection conn = Student.getConnection()) {
	            if (conn == null) {
	                JOptionPane.showMessageDialog(this, "Database connection failed!");
	                return;
	            }

	            String query = "SELECT id, name, email, gender FROM students";
	            PreparedStatement stmt = conn.prepareStatement(query);
	            ResultSet rs = stmt.executeQuery();

	            model.setRowCount(0); // Clear table
	            maleCount = 0;
	            femaleCount = 0;

	            while (rs.next()) {
	                int id = rs.getInt("id");
	                String name = rs.getString("name");
	                String email = rs.getString("email");
	                String gender = rs.getString("gender");

	                model.addRow(new Object[]{id, name, email, gender});

	                if ("Male".equalsIgnoreCase(gender)) maleCount++;
	                else if ("Female".equalsIgnoreCase(gender)) femaleCount++;
	            }

	            repaint(); // Refresh chart

	        } catch (Exception e) {
	            e.printStackTrace();
	            JOptionPane.showMessageDialog(this, "Error loading data: " + e.getMessage());
	        }
	    }

	    public static void main(String[] args) {
	        new Dashboard();
	    }
	}

