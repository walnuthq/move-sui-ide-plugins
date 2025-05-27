module basics::clock;

use sui::clock::Clock;
use sui::event;

public struct TimeEvent has copy, drop, store {
    timestamp_ms: u64,
}

entry fun access(clock: &Clock) {
    event::emit(TimeEvent { timestamp_ms: clock.timestamp_ms() });
}

module basics::random;

use sui::event;
use sui::random::Random;

public struct RandomU128Event has copy, drop {
    value: u128,
}

entry fun new(r: &Random, ctx: &mut TxContext) {
    let mut gen = r.new_generator(ctx);
    let value = gen.generate_u128();
    event::emit(RandomU128Event { value });
}
