package spittr.data;

import org.springframework.stereotype.Component;
import spittr.Spitter;
import spittr.Spittle;

import java.util.*;

@Component
public class SpittleRepositoryImpl implements SpittleRepository {

    private List<Spittle> spittles;
    private Map<Long, Spitter> spitterMap = new HashMap<>();

    @Override
    public List<Spittle> findSpittles(long max, int count) {
        if (spittles == null) {
            init(count);
        }

        return spittles;
    }

    @Override
    public Spittle findOne(long spittleId) {
        if (spittles == null) {
            init(20);
        }

        return spittles.get((int) spittleId);
    }

    @Override
    public Spitter save(Spitter spitter) {
        Random random = new Random();
        long id = random.nextLong();

        spitter.setId(id);
        spitterMap.put(id, spitter);

        return spitter;
    }

    @Override
    public Spitter findSpitterOne(long spittleId) {
        return spitterMap.get(spittleId);
    }

    @Override
    public Spitter findByUsername(String username) {
        final Spitter[] spitters = new Spitter[1];
        spitterMap.forEach((key, value) -> {
            if (value.getUsername().equals(username)) {
                spitters[0] = value;
            }
        });
        return spitters.length > 0 ? spitters[0] : new Spitter();
    }

    private void init(int count) {
        spittles = new ArrayList<Spittle>();
        for (int i = 0; i < count; i++) {
            spittles.add(new Spittle("Spittle " + i, new Date()));
        }
    }
}
