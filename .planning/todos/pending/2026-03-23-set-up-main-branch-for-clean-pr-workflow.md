---
created: 2026-03-23T16:40:17.395Z
title: Set up main branch for clean PR workflow
area: tooling
files: []
---

## Problem

The repository has no `main` branch. All work is on `develop`, which also has `.planning/` files committed into it. This makes `/gsd:pr-branch` messy — the PR diff includes planning file deletions because `origin/develop` already has them.

The ideal GSD setup: `main` branch contains only code (no `.planning/`), and PRs are opened from feature/fix branches targeting `main`.

## Solution

- Create a `main` branch from a clean point in `develop`'s history (before `.planning/` was first committed)
- Or create `main` from scratch with just the code files
- Set `main` as the default branch on GitHub
- Future PRs: `develop-pr` → `main` (clean diff, no planning noise)
- Consider adding `.planning/` to `.gitignore` on `main` to prevent accidental commits
