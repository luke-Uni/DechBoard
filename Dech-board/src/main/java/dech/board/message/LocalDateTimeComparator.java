package dech.board.message;

import java.time.LocalDateTime;
import java.util.Comparator;

// Compares the dates of messages and orders them descending 
public class LocalDateTimeComparator implements Comparator < LocalDateTime >
{

    @Override
    public int compare ( LocalDateTime o1 , LocalDateTime o2 )
    {
        // Compare the date portion first. If equal, then look at time-of-day.
        int result = o1.toLocalDate().compareTo( o2.toLocalDate() ); // Consider only the date portion first.
        result = ( ( - 1 ) * result ); // Flip the positive/negative sign of the int, to get ascending order. Or more simply: `= - result ;`.
        if ( 0 == result ) // If dates are equal, look at the time-of-day.
        {
            System.out.println( "reversing " );
            result = o1.toLocalTime().compareTo( o2.toLocalTime() );
        }
        return result;
    }
}