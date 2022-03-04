import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Sos implements ActionListener
{
   Point[] points = new Point[1000];
   int pointCount = 0;
   int x1 = -1, y1, x2, y2;
   
   JFrame frame = new JFrame("SOS Game");
   int [][] board = new int[10][10];
   Drawing draw = new Drawing();
   
   ImageIcon blankImage = new ImageIcon("blank.jpg");
   ImageIcon oImage = new ImageIcon("o.jpg");
   ImageIcon sImage = new ImageIcon("s.jpg");
   
   int score1 = 0, score2 = 0;
   JButton player1 = new JButton("Player One");
   JButton player2 = new JButton("Player Two");
   JLabel p1Score = new JLabel("" + score1);
   JLabel p2Score = new JLabel("" + score2);
   
   
   
   public Sos()
   {
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setSize(517,585);
      draw.addMouseListener(new MouseListen());
   
      frame.add(draw);
      
      player1.addActionListener(this);
      player2.addActionListener(this);
      
      p1Score.setFont(new Font("Serif", Font.BOLD, 26));
      p2Score.setFont(new Font("Serif", Font.BOLD, 26));
      
      JPanel bottom = new JPanel();
      bottom.add(player1);
      bottom.add(p1Score);
      bottom.add(player2);
      bottom.add(p2Score);
      frame.add(bottom, "South");
     
      frame.setVisible(true);
   }
   
   class MouseListen extends MouseAdapter
   {
     public void mousePressed (MouseEvent e)
     {
        x1 = e.getX();
        y1 = e.getY();
     }
    public void mouseReleased (MouseEvent e)
      {
         x2 = e.getX();
         y2 = e.getY();
         if (Math.abs(x2 - x1) < 3 && Math.abs(y2 - y1) < 3)
         {
            board[x2/50][y2/50]++;
         }
         else 
         {
            points[pointCount++] = new Point(x1, y1);
            points[pointCount++] = new Point(x2, y2);
         }
         draw.repaint();
     }
   } 
   
   class Drawing extends JComponent
   {
      public void paint(Graphics g)
      {
         g.setColor(Color.white);
         g.fillRect(0,0, 500,500);
         for (int i = 0; i <= 10; i++)
         {
            g.setColor(Color.black);
            g.drawLine(0, i*50, 500, i*50);
            g.drawLine(i*50, 0, i*50, 500);
         }
         for (int i = 0; i <= 9; i++)
            for (int j = 0; j <= 9; j++)
            {
            int n = (board[i][j] % 3);
               if (n == 0)
                  g.drawImage(blankImage.getImage(), i * 50 + 1, j * 50 + 1, 49, 49, this);
               else if (n == 1)
                  g.drawImage(sImage.getImage(), i * 50 + 1, j * 50 + 1, 49, 49, this);
               else if (n == 2)
                  g.drawImage(oImage.getImage(), i * 50 + 1, j * 50 + 1, 49, 49, this); 
            }
         for (int i = 0; i < pointCount; i += 2)
             g.drawLine(points[i].x, points[i].y, points[i + 1].x, points[i + 1].y);
      }
   }  
               
   public void actionPerformed(ActionEvent e)
   {
      if (e.getSource() == player1)
      {
         score1 += 1;
         p1Score.setText("" + score1);
      }
      else if (e.getSource() == player2)
      {
         score2 += 1; 
         p2Score.setText("" + score2);
      }
   } 
   
   public static void main(String[] args)
   {
      new Sos();
   }     
}