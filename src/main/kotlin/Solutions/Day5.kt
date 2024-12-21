package org.example.Solutions

import org.example.Common.AoCSolution

class Day5: AoCSolution() {
    override val day: String
        get() = "5"

    override fun FirstSolution() {
        val input = readInputAsListOfLines()
        val result = CalculateFirst(input)
        print(result)
    }

    override fun SecondSolution() {
        val input = readInputAsListOfLines()
        val result = CalculateSecond(input)
        print(result)
    }

    fun CalculateFirst(input: List<String>): Int {
        val rulesPattern = """\d+\|\d+""".toRegex()
        val orderingPattern = """(\d+,)+\d+""".toRegex()

        val rules = input.filter { x -> rulesPattern.matches(x) }.map{y -> _parseRule(y)}
        val orderings = input.filter { x -> orderingPattern.matches(x) }.map{y -> _parseOrderingRule(y)}

        val validOrderings = orderings.filter { o -> _isOrderingRuleCorrect(rules, o) }

        return validOrderings.map{ x -> x[x.lastIndex/2] }.sum()
    }

    fun CalculateSecond(input: List<String>): Int {
        val rulesPattern = """\d+\|\d+""".toRegex()
        val orderingPattern = """(\d+,)+\d+""".toRegex()

        val rules = input.filter { x -> rulesPattern.matches(x) }.map{y -> _parseRule(y)}
        val orderings = input.filter { x -> orderingPattern.matches(x) }.map{y -> _parseOrderingRule(y)}

        val fixedOrderings = orderings.filter { o -> !_isOrderingRuleCorrect(rules, o) }.map{ x -> _reorderRules(rules, x)}

        return fixedOrderings.map{ x -> x[x.lastIndex/2] }.sum()
    }

    private fun _parseRule(rule: String): Pair<Int, Int> {
        val (n, m) = rule.split("|").map{x -> x.toInt()}
        return Pair(n, m)
    }

    private fun _parseOrderingRule(rule: String): List<Int> {
        return rule.split(",").map{x -> x.toInt()}
    }

    private fun _isOrderingRuleCorrect(rules: List<Pair<Int, Int>>, ordering: List<Int>): Boolean {
        for(r in rules) {
            if(ordering.indexOf(r.first) == -1 || ordering.indexOf(r.second) == -1) {
                continue
            }
            if(ordering.indexOf(r.first) > ordering.indexOf(r.second)) {
                return false
            }
        }
        return true
    }

    private fun _reorderRules(rules: List<Pair<Int, Int>>, ordering: List<Int>): List<Int> {
        var result = ordering.toMutableList()
        var ruleIndex = 0
        while(!_isOrderingRuleCorrect(rules, result)) {
            if(!result.contains(rules[ruleIndex].first) || !result.contains(rules[ruleIndex].second) ) {
                if(ruleIndex + 1 > rules.lastIndex) {
                    ruleIndex = 0
                }
                else {
                    ruleIndex += 1
                }
                continue
            }
            var firstIndex = result.indexOf(rules[ruleIndex].first)
            var secondIndex = result.indexOf(rules[ruleIndex].second)
            if(firstIndex > secondIndex) {
                result.add(secondIndex, rules[ruleIndex].first)
                result.removeAt(firstIndex+1)
            }
            if(ruleIndex + 1 > rules.lastIndex) {
                ruleIndex = 0
            }
            else {
                ruleIndex += 1
            }
        }
        return result
    }
}