package spittr.data;

import spittr.Spitter;
import spittr.Spittle;

import java.util.List;

public interface SpittleRepository {
    List<Spittle> findSpittles(long max, int count);

    Spittle findOne(long spittleId);

    Spitter save(Spitter spitter);

    Spitter findSpitterOne(long spittleId);

    Spitter findByUsername(String username);

}
