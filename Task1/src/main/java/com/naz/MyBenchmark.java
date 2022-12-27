/*
 * Copyright (c) 2014, Oracle America, Inc.
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 *  * Redistributions of source code must retain the above copyright notice,
 *    this list of conditions and the following disclaimer.
 *
 *  * Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in the
 *    documentation and/or other materials provided with the distribution.
 *
 *  * Neither the name of Oracle nor the names of its contributors may be used
 *    to endorse or promote products derived from this software without
 *    specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF
 * THE POSSIBILITY OF SUCH DAMAGE.
 */

package com.naz;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.infra.Blackhole;

import java.util.*;  
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class MyBenchmark {

    @State(Scope.Benchmark)
    public static class MyState {
        Random rand = new Random();
        HashSet<Integer> hset = new HashSet<Integer>(); 
        TreeSet <Integer> tset = new TreeSet<Integer>();
        LinkedHashSet<Integer> linkedset = new LinkedHashSet<Integer>();
    }
  

    //Insert
    @Benchmark @BenchmarkMode(Mode.All) @OutputTimeUnit(TimeUnit.SECONDS)
    @Warmup(iterations = 2)
    @Measurement(iterations = 5)
    @Fork(value = 1, warmups = 1)
    public void HashSetInsert(MyState state) {

        for (int i=0; i<1000000; i++){
            state.hset.add(state.rand.nextInt(10000));
        }
    }

    @Benchmark @BenchmarkMode(Mode.All) @OutputTimeUnit(TimeUnit.SECONDS)
    @Warmup(iterations = 2)
    @Measurement(iterations = 5)
    @Fork(value = 1, warmups = 1)
    public void TreeSetInsert(MyState state) {
          
        for (int i=0; i<1000000; i++){
            state.tset.add(state.rand.nextInt(10000));
        }
    }

    @Benchmark @BenchmarkMode(Mode.All) @OutputTimeUnit(TimeUnit.SECONDS)
    @Warmup(iterations = 2)
    @Measurement(iterations = 5)
    @Fork(value = 1, warmups = 1)
    public void LinkedHashSetInsert(MyState state) {
        
        for (int i=0; i<1000000; i++){
            state.linkedset.add(state.rand.nextInt(10000));
        }
    }

    // Search (Contain)
    @Benchmark @BenchmarkMode(Mode.All) @OutputTimeUnit(TimeUnit.SECONDS)
    @Warmup(iterations = 2)
    @Measurement(iterations = 5)
    @Fork(value = 1, warmups = 1)
    public void HashSetSearch(MyState state, Blackhole bh) {
        bh.consume(state.hset.contains(state.rand.nextInt(10000)));
    }

    @Benchmark @BenchmarkMode(Mode.All) @OutputTimeUnit(TimeUnit.SECONDS)
    @Warmup(iterations = 2)
    @Measurement(iterations = 5)
    @Fork(value = 1, warmups = 1)
    public void TreeSetSearch(MyState state, Blackhole bh) {
        bh.consume(state.tset.contains(state.rand.nextInt(10000)));
    }

    @Benchmark @BenchmarkMode(Mode.All) @OutputTimeUnit(TimeUnit.SECONDS)
    @Warmup(iterations = 2)
    @Measurement(iterations = 5)
    @Fork(value = 1, warmups = 1)
    public void LinkedHashSetSearch(MyState state, Blackhole bh) {
        bh.consume(state.linkedset.contains(state.rand.nextInt(10000)));
    }

    // Sort
    @Benchmark @BenchmarkMode(Mode.All) @OutputTimeUnit(TimeUnit.SECONDS)
    @Warmup(iterations = 2)
    @Measurement(iterations = 5)
    @Fork(value = 1, warmups = 1)
    public void HashSetSort(MyState state, Blackhole bh) {
        bh.consume(state.hset.stream().sorted());
    }

    @Benchmark @BenchmarkMode(Mode.All) @OutputTimeUnit(TimeUnit.SECONDS)
    @Warmup(iterations = 2)
    @Measurement(iterations = 5)
    @Fork(value = 1, warmups = 1)
    public void TreeSetSort(MyState state, Blackhole bh) {
        bh.consume(state.tset.stream().sorted());
    }

    @Benchmark @BenchmarkMode(Mode.All) @OutputTimeUnit(TimeUnit.SECONDS)
    @Warmup(iterations = 2)
    @Measurement(iterations = 5)
    @Fork(value = 1, warmups = 1)
    public void LinkedHashSetSort(MyState state, Blackhole bh) {
        bh.consume(state.linkedset.stream().sorted());
    }

}
