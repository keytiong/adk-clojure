---
created: 2026-03-23T16:08:40.134Z
title: Prepare to cut release 0.1.0
area: general
files: []
---

## Problem

The project is maturing and needs a 0.1.0 release. This involves ensuring the core and dev libraries are production-ready, versioned correctly, and published to a registry (Clojars or similar). Pre-release tasks likely include: finalizing version numbers in deps.edn/build.clj, ensuring the changelog/README is accurate, running all tests, verifying the JAR build is clean, and tagging the release in git.

## Solution

TBD — likely involves:
- Audit all deps.edn files for version strings
- Update CHANGELOG or release notes
- Run full build (`clojure -T:build jar`) for both core and dev
- Tag git commit as `v0.1.0`
- Publish JARs to Clojars (if public) or local Maven repo
